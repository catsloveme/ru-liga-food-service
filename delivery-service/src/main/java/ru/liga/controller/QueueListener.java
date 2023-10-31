package ru.liga.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import ru.liga.clients.OrderFeign;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.liga.dto.response.OrderResponse;
import ru.liga.entity.Courier;
import ru.liga.enums.StatusCourier;
import ru.liga.enums.StatusOrder;
import ru.liga.exception.DataNotFoundException;
import ru.liga.repository.CourierRepository;
import ru.liga.service.jpa.JpaCourierService;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Класс получателя сообщений.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class QueueListener {
    private final CourierRepository jpaCourierRepository;
    private final JpaCourierService jpaCourierService;
    private final OrderFeign orderFeign;
    private final RetryTemplate retryTemplate;
    private final ObjectMapper objectMapper;

    /**
     * Метод, отвечающий за получение сообщения из очереди courierSearchQueueToCourier о поиске курьера и
     * запуск поиска ближайшего курьера.
     * @param idOrder идентификатор заказа
     * @throws JsonProcessingException
     */
    @RabbitListener(queues = "courierSearchQueueToCourier")
    public void getSearchingMessage(String idOrder) throws JsonProcessingException {
        log.info("The message about searching courier is received");
        Long gettingIdOrder = objectMapper.readValue(idOrder, Long.class);

        searchNearestCouriers(gettingIdOrder);

    }

    /**
     * Поиск свободных курьеров, если таких нет, то поиск возобновится через 10 минут.
     * Если в течении часа свободные курьеры найдены, то среди них ищется ближайший к ресторану.
     * @param idOrder идентификатор заказа
     */
    public void searchNearestCouriers(Long idOrder) {

        List<Courier> activeCouriers = jpaCourierRepository.findByStatus(StatusCourier.DELIVERY_PENDING);
        String restaurantAddress = searchRestaurantAddress(idOrder);

        if (!activeCouriers.isEmpty()) {
            searchNearestCouriersAndChangeOrderStatus(activeCouriers, restaurantAddress, idOrder);
        } else {
            log.info("The courier search will be re-attempted in 10 minutes");
            retryTemplate.execute(arg0 -> {
                searchNearestCouriers(idOrder);
                return null;
            });
        }
    }

    /**
     * Поиск ближайшего курьера и изменение его статуса.
     * @param activeCouriers список свободных курьеров
     * @param restaurantAddress координаты ресторана
     * @param idOrder идентификатор заказа
     */
    public void searchNearestCouriersAndChangeOrderStatus(
        List<Courier> activeCouriers,
        String restaurantAddress,
        Long idOrder
    ) {
        Long courierIdForDelivery = choseNearestCourierId(activeCouriers, restaurantAddress);
        log.info("A courier id = {} has been selected for the order id = {}", courierIdForDelivery, idOrder);
        orderFeign.updateCourierId(courierIdForDelivery, idOrder);
        orderFeign.updateOrderStatus(courierIdForDelivery, StatusOrder.DELIVERY_PICKING);
        jpaCourierService.changeOrderStatusById(courierIdForDelivery, StatusCourier.DELIVERY_PICKING);
    }

    /**
     * Поиск координат ресторана через id заказа.
     * @param idOrder идентификатор заказа
     * @return координаты ресторана
     */
    public String searchRestaurantAddress(Long idOrder) {
        ResponseEntity<OrderResponse> orderResponseEntity = orderFeign.findOrderById(idOrder);
        OrderResponse orderResponse = orderResponseEntity.getBody();
        String restaurantAddress = Optional
            .ofNullable(orderResponse)
            .map(it -> it.getRestaurant().getAddress())
            .orElseThrow(() -> new DataNotFoundException(String.format(
                "order response by id = %d not found",
                idOrder
            )));
        return restaurantAddress;
    }

    /**
     * Выбор ближайшего курьера.
     * @param activeCouriers список курьеров
     * @param restaurantAddress координаты ресторана
     * @return идентификатор курьера
     */
    public Long choseNearestCourierId(List<Courier> activeCouriers, String restaurantAddress) {
        Long nearestCourierId = null;
        Double minimumDistance = Double.MAX_VALUE;
        Double currentDistance;
        for (Courier courier : activeCouriers) {
            currentDistance = calculateDistanceBetweenTwoPoints(restaurantAddress, courier.getCoordinates());
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

