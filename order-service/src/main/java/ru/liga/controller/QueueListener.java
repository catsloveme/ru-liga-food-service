package ru.liga.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.liga.api.OrderService;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.service.rabbitMQ.NotificationService;

/**
 * Класс получателя сообщений.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class QueueListener {
    private final OrderService orderService;
    private final NotificationService notificationService;
    private final ObjectMapper objectMapper;

    /**
     * Метод, отвечающий за получение сообщения из очереди createOrderQueueToOrder о новом заказе и
     * отправку сообщения notification-service о новом заказе.
     *
     * @param request данные ответа о создании нового заказа
     */
    @RabbitListener(queues = "createOrderQueueToOrder")
    public void processQueueCreateOrder(String request) throws IOException {
        CreateOrderRequest createOrderRequest = objectMapper.readValue(request, CreateOrderRequest.class);
        CreateOrderResponse response = orderService.addOrder(createOrderRequest);
        log.info("Received from createOrderQueue : " + response);
        notificationService.sendCreateOrder(response.getId());
    }

}

