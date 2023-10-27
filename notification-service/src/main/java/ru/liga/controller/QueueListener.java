package ru.liga.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.liga.service.rabbitMQ.CourierService;
import ru.liga.service.rabbitMQ.RestaurantService;


@Service
@Slf4j
@RequiredArgsConstructor
public class QueueListener {
    public final RestaurantService restaurantService;
    public final CourierService courierService;

    @RabbitListener(queues = "newOrderQueueToNotification")
    public void processQueueCreateOrder(String orderId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Long createOrderRequest = objectMapper.readValue(orderId, Long.class);
        restaurantService.sendMessageCreate(createOrderRequest);

    }
    @RabbitListener(queues = "courierSearchQueueToNotification")
    public void searchCouriers(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Long idOrder = objectMapper.readValue(message, Long.class);
        courierService.sendMessageSearch(idOrder);
    }

}

