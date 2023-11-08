package ru.liga.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.liga.dto.CreateOrderResponse;
import ru.liga.dto.ResponseAndKey;
import ru.liga.service.rabbitMQ.CourierService;
import ru.liga.service.rabbitMQ.OrderService;
import ru.liga.service.rabbitMQ.RestaurantService;

/**
 * Класс получателя сообщений.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class QueueListener {
    public final RestaurantService restaurantService;
    public final CourierService courierService;
    public final OrderService orderService;
    public final ObjectMapper objectMapper;
    public String KEY;

    /**
     * Метод, отвечающий за получение сообщения из очереди newOrderQueueToNotification о новом заказе и
     * отправку сообщения restaurant-service о новом заказе.
     *
     * @param response ответ создание заказа
     */
    @RabbitListener(queues = "toNotification")
    public void processQueueCreateOrder(String response) throws JsonProcessingException {
        ResponseAndKey responseAndKey = objectMapper.readValue(response, ResponseAndKey.class);
        KEY = responseAndKey.getKey();
        Long orderId;
        String message;
        log.info("Получено сообщение {} с ключом {}", responseAndKey.getResponse(), KEY);
        switch (KEY) {
            case ("kitchen"):
                message = responseAndKey.getResponse();
                orderId = responseAndKey.getId();
                restaurantService.sendMessageCreate(orderId, message);
                break;
            case ("order"):
                message = objectMapper.readValue(responseAndKey.getResponse(), String.class);
                orderId = responseAndKey.getId();
                orderService.sendMessageOrder(orderId, message);
                break;
            case ("courier"):
                String addressRestaurant = objectMapper.readValue(responseAndKey.getResponse(), String.class);
                orderId = responseAndKey.getId();
                courierService.sendMessageSearch(orderId, addressRestaurant);
                break;
            case ("kitchen_about_courier"):
                orderId = responseAndKey.getId();
                Long courierId = objectMapper.readValue(responseAndKey.getResponse(), Long.class);
                restaurantService.sendMessageAboutSearchingCourier(orderId, courierId);
                break;

        }
//kitchen_delivered
//    /**
//     * Метод, отвечающий за получение сообщения из очереди courierSearchQueueToNotification о поиске курьера и
//     * отправку сообщения courier-service о поиске курьера.
//     *
//     * @param response ответ заказа
//     */
//    @RabbitListener(queues = "courierSearchQueueToNotification")
//    public void searchCouriers(String response) throws JsonProcessingException {
//        CreateOrderResponse createOrderResponse = objectMapper.readValue(response, CreateOrderResponse.class);
//        courierService.sendMessageSearch(createOrderResponse);
//    }
//
//    /**
//     * Метод, отвечающий за получение сообщения из очереди updateStatus о том, что найден курьер и
//     * отправку сообщения order-service о смене статуса заказа.
//     *
//     * @param orderId идентификатор заказа
//     */
//    @RabbitListener(queues = "updateStatus")
//    public void updateStatus(String orderId, String courierId) throws JsonProcessingException {
//        Long idOrder = objectMapper.readValue(orderId, Long.class);
//        Long idCourier = objectMapper.readValue(courierId, Long.class);
//        orderService.sendMessageUpdateStatusDeliveryPicking(idOrder, idCourier);
//    }
//
//    @RabbitListener(queues = "orderUpdateStatusToNotificationFromRestaurant")
//    public void updateStatusOrder(String orderId) throws JsonProcessingException {
//        Long idOrder = objectMapper.readValue(orderId, Long.class);
//        orderService.sendMessageUpdateStatusKitchenAccepted(idOrder);
//    }

    }
}

