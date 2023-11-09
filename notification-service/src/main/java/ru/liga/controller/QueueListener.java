package ru.liga.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.liga.dto.ResponseAndKey;
import ru.liga.service.rabbitMQ.CourierService;
import ru.liga.service.rabbitMQ.OrderService;
import ru.liga.service.rabbitMQ.RestaurantService;
import java.util.UUID;

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
    public void processQueueCreateOrder(String response) {
        ResponseAndKey responseAndKey = null;
        try {
            responseAndKey = objectMapper.readValue(response, ResponseAndKey.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        KEY = responseAndKey.getKey();
        UUID orderId;
        String message;
        log.info("Получено сообщение {} с ключом {}", responseAndKey.getResponse(), KEY);
        switch (KEY) {
            case ("kitchen"):
                message = responseAndKey.getResponse();
                orderId = responseAndKey.getId();
                restaurantService.sendMessageCreate(orderId, message);
                break;
            case ("order"):
                message =responseAndKey.getResponse();
                orderId = responseAndKey.getId();
                orderService.sendMessageOrder(orderId, message);
                break;
            case ("courier"):
                String addressRestaurant = responseAndKey.getResponse();
                orderId = responseAndKey.getId();
                courierService.sendMessageSearch(orderId, addressRestaurant);
                break;
            case ("kitchen_about_courier"):
                orderId = responseAndKey.getId();
                UUID courierId = UUID.fromString(responseAndKey.getResponse());
                restaurantService.sendMessageAboutSearchingCourier(orderId, courierId);
                break;

        }


    }
}

