package ru.liga.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.liga.api.RestaurantService;
import ru.liga.enums.StatusOrder;
import ru.liga.service.rabbitMQ.NotificationService;

/**
 * Класс получателя сообщений.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class QueueListener {
    private final ObjectMapper objectMapper;
    private static final String MESSAGE_COURIER_FOUND
        = "Курьер для доставки заказа номер {} найден. Ожидайте доставку";
    private static final String MESSAGE_COURIER_NOT_FOUND
        = "Курьер для доставки заказа номер {} не найден. Заказ Отменен";
    private final NotificationService notificationService;
    private final RestaurantService restaurantService;

    /**
     * Метод, отвечающий за получение сообщения из очереди newOrderQueueToRestaurant о новом заказе и
     * зменение статуса заказа.
     *
     * @param pairMessage пара, состоящая из идентификатора заказа и сообщения заказчику
     */
    @RabbitListener(queues = "restaurant")
    public void processQueueCreateOrder(String pairMessage) throws IOException {
        String strWithoutBrackets = pairMessage.substring(1, pairMessage.length() - 1);
        String[] arrayOrderIdAndCourierId = strWithoutBrackets.split(":");

        Long orderId = objectMapper.readValue(arrayOrderIdAndCourierId[0], Long.class);
        String message = arrayOrderIdAndCourierId[1];
        log.info(message, orderId);

    }

    @RabbitListener(queues = "resultSearchingCourier")
    public void resultSearchCourier(String pairMessage) throws JsonProcessingException {
        String strWithoutBrackets = pairMessage.substring(1, pairMessage.length() - 1);
        String[] arrayOrderIdAndCourierId = strWithoutBrackets.split(":");

        UUID orderId = objectMapper.readValue(arrayOrderIdAndCourierId[0], UUID.class);
        UUID courierId = objectMapper.readValue(arrayOrderIdAndCourierId[1], UUID.class);

        if (courierId == null) {
            notificationService.sendMessageOrder(MESSAGE_COURIER_NOT_FOUND, orderId);
            restaurantService.updateOrderStatus(StatusOrder.KITCHEN_DENIED, orderId);
            log.info("Получено сообщение о том, что курьер не найден. Заказ отменен, заказчику отправлено уведомление.");
        } else {
            notificationService.sendMessageOrder(MESSAGE_COURIER_FOUND, orderId);
            restaurantService.updateOrderStatus(StatusOrder.DELIVERY_PICKING, orderId);
            restaurantService.updateCourierId(courierId, orderId);
            log.info(
                "Получено сообщение о том, что курьер найден. Заказ готовится к отправке, заказчику отправлено уведомление.");
        }

    }

}

