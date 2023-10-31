package ru.liga.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.liga.service.rabbitMQ.CourierService;
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
    public final ObjectMapper objectMapper;

    /**
     * Метод, отвечающий за получение сообщения из очереди newOrderQueueToNotification о новом заказе и
     * отправку сообщения restaurant-service о новом заказе.
     * @param orderId идентификатор заказа
     * @throws JsonProcessingException
     */
    @RabbitListener(queues = "newOrderQueueToNotification")
    public void processQueueCreateOrder(String orderId) throws JsonProcessingException {
        Long createOrderRequest = objectMapper.readValue(orderId, Long.class);
        restaurantService.sendMessageCreate(createOrderRequest);

    }

    /**
     * Метод, отвечающий за получение сообщения из очереди courierSearchQueueToNotification о поиске курьера и
     * отправку сообщения courier-service о поиске курьера.
     * @param orderId идентификатор заказа
     * @throws JsonProcessingException
     */
    @RabbitListener(queues = "courierSearchQueueToNotification")
    public void searchCouriers(String orderId) throws JsonProcessingException {
        Long idOrder = objectMapper.readValue(orderId, Long.class);
        courierService.sendMessageSearch(idOrder);
    }

}

