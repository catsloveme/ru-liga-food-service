package ru.liga.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import ru.liga.api.CourierService;
import ru.liga.dto.response.CourierResponse;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.enums.StatusCourier;
import ru.liga.exception.NotFoundFreeCourierException;
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
    private final int maxAttempts = 6;

    /**
     * Метод, отвечающий за получение сообщения из очереди courierSearchQueueToCourier о поиске курьера и
     * запуск поиска ближайшего курьера.
     *
     * @param response ответ заказа
     */
    @RabbitListener(queues = "courierSearchQueueToCourier")
    public void getSearchingMessage(String response) throws JsonProcessingException {
        log.info("The message about searching courier is received");
        CreateOrderResponse createOrderResponse = objectMapper.readValue(response, CreateOrderResponse.class);

        searchNearestCouriers(createOrderResponse);

    }

    /**
     * Поиск свободных курьеров, если таких нет, то поиск возобновится через 10 минут.
     * Если в течении часа свободные курьеры найдены, то среди них ищется ближайший к ресторану.
     *
     * @param createOrderResponse ответ заказа
     */
    @Retryable(value = NotFoundFreeCourierException.class,
               maxAttempts = maxAttempts, backoff = @Backoff(delay = 600_000L))
    public void searchNearestCouriers(CreateOrderResponse createOrderResponse) {

        List<CourierResponse> activeCouriers = courierService.findByStatus(StatusCourier.DELIVERY_PENDING);
        Long orderId = createOrderResponse.getId();
        String restaurantAddress = createOrderResponse.getAddress();

        if (!activeCouriers.isEmpty()) {
            searchNearestCouriersAndChangeOrderStatus(activeCouriers, restaurantAddress, orderId);
        } else {
            log.info("The courier search will be re-attempted in 10 minutes");

            throw new NotFoundFreeCourierException("Нет свободных курьеров");
        }
    }

    @Recover
    void recover(Long idOrder) {
        log.info("Сообщение о том, что курьеры не найдены в течении часа отправлено в ресторан");
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
        Long idOrder
    ) {
        Long courierIdForDelivery = choseNearestCourierId(activeCouriers, restaurantAddress);
        log.info("A courier id = {} has been selected for the order id = {}", courierIdForDelivery, idOrder);

        notificationService.sendMessageUpdate(idOrder, courierIdForDelivery);
        //orderFeign.updateCourierId(courierIdForDelivery, idOrder);
        // orderFeign.updateOrderStatus(courierIdForDelivery, StatusOrder.DELIVERY_PICKING);
        courierService.changeOrderStatusById(courierIdForDelivery, StatusCourier.DELIVERY_PICKING);
    }

    /**
     * Выбор ближайшего курьера.
     * @param activeCouriers список курьеров
     * @param restaurantAddress координаты ресторана
     * @return идентификатор курьера
     */
    public Long choseNearestCourierId(List<CourierResponse> activeCouriers, String restaurantAddress) {
        Long nearestCourierId = null;
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

