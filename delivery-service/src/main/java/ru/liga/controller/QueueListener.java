package ru.liga.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.liga.api.CourierService;
import ru.liga.dto.response.CourierResponse;
import ru.liga.enums.StatusCourier;
import ru.liga.service.rabbitMQ.NotificationService;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Класс получателя сообщений.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class QueueListener {
    private final CourierService courierService;
    private final ObjectMapper objectMapper;
    private final NotificationService notificationService;


    /**
     * Метод, отвечающий за получение сообщения из очереди courierSearchQueueToCourier о поиске курьера и
     * запуск поиска ближайшего курьера.
     *
     * @param pairMessage пара состоящая из идентификатора заказа и адреса ресторана
     */
    @RabbitListener(queues = "courierSearchQueueToCourier")
    public void getSearchingMessage(String pairMessage) throws JsonProcessingException {

        String strWithoutBrackets = pairMessage.substring(1, pairMessage.length() - 1);
        String[] arrayOrderIdAndCourierId = strWithoutBrackets.split(":");
        UUID orderId = objectMapper.readValue(arrayOrderIdAndCourierId[0], UUID.class);
        String addressRestaurant = objectMapper.readValue(arrayOrderIdAndCourierId[1], String.class);
        log.info("Получено сообщение с адресом ресторана: {}, для заказа {}", addressRestaurant, orderId);
        searchNearestCouriers(orderId, addressRestaurant);
    }

    /**
     * Поиск свободных курьеров, если таких нет, то заказ отменяется.
     * Если в течении часа свободные курьеры найдены, то среди них ищется ближайший к ресторану.
     *
     * @param orderId           идентификатор заказа
     * @param addressRestaurant адрес ресторана
     */

    public void searchNearestCouriers(UUID orderId, String addressRestaurant) {

        List<CourierResponse> activeCouriers = courierService.findByStatus(StatusCourier.DELIVERY_PENDING);

        if (!activeCouriers.isEmpty()) {
            searchNearestCouriersAndChangeOrderStatus(activeCouriers, addressRestaurant, orderId);
        } else {
            notificationService.sendMessage(orderId, orderId);
            log.info("Нет доступных курьеров, заказ необходимо отменить.");

        }
    }

    /**
     * Поиск ближайшего курьера и изменение его статуса.
     *
     * @param activeCouriers    список свободных курьеров
     * @param restaurantAddress координаты ресторана
     * @param idOrder           идентификатор заказа
     */
    public void searchNearestCouriersAndChangeOrderStatus(
        List<CourierResponse> activeCouriers,
        String restaurantAddress,
        UUID idOrder
    ) {
        UUID courierIdForDelivery = choseNearestCourierId(activeCouriers, restaurantAddress);
        log.info("Курьер id = {} был выбран для заказа id = {}", courierIdForDelivery, idOrder);
        notificationService.sendMessage(idOrder, courierIdForDelivery);
        courierService.changeStatusById(courierIdForDelivery, StatusCourier.DELIVERY_PICKING);
    }

    /**
     * Выбор ближайшего курьера.
     *
     * @param activeCouriers    список курьеров
     * @param restaurantAddress координаты ресторана
     * @return идентификатор курьера
     */
    public UUID choseNearestCourierId(List<CourierResponse> activeCouriers, String restaurantAddress) {
        UUID nearestCourierId = null;
        Double minimumDistance = Double.MAX_VALUE;
        Double currentDistance;
        for (CourierResponse courier : activeCouriers) {
            currentDistance = calculateDistanceBetweenTwoPoints(restaurantAddress, courier.getAddress());
            if (currentDistance < minimumDistance) {
                minimumDistance = currentDistance;
                nearestCourierId = courier.getId();
            }
        }
        return nearestCourierId;
    }

    /**
     * Вычисление расстояния между двумя точками, заданными координатами в двумерном пространстве.
     * @param firstPoint координаты первой точки
     * @param secondPoint координаты второй точки
     * @return расстояние между исходными точками
     */
    public Double calculateDistanceBetweenTwoPoints(String firstPoint, String secondPoint) {

        String[] coordinateFirstPoint = firstPoint.split(",");
        String[] coordinateSecondPoint = secondPoint.split(",");
        Double firstPointX = Double.valueOf(coordinateFirstPoint[0]);
        Double firstPointY = Double.valueOf(coordinateFirstPoint[1]);

        Double secondPointX = Double.valueOf(coordinateSecondPoint[0]);
        Double secondPointY = Double.valueOf(coordinateSecondPoint[1]);

        Double distention = sqrt(pow(firstPointX - secondPointX, 2) + pow(firstPointY - secondPointY, 2));
        return distention;

    }
}

