package ru.liga.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.liga.clients.OrderFeign;
import ru.liga.enums.StatusOrder;

/**
 * Класс получателя сообщений.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class QueueListener {
    private final OrderFeign orderFeign;
    private final ObjectMapper objectMapper;

    /**
     * Метод, отвечающий за получение сообщения из очереди newOrderQueueToRestaurant о новом заказе и
     * зменение статуса заказа.
     *
     * @param orderId идентификатор заказа
     */
    @RabbitListener(queues = "newOrderQueueToRestaurant")
    public void processQueueCreateOrder(String orderId) throws IOException {
        Long id = objectMapper.readValue(orderId, Long.class);
        orderFeign.updateOrderStatus(id, StatusOrder.KITCHEN_ACCEPTED);

    }

}

