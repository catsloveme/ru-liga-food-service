package ru.liga.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.liga.dto.response.CreateOrderResponse;

/**
 * Класс получателя сообщений.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class QueueListener {
    private final ObjectMapper objectMapper;

    /**
     * Метод, отвечающий за получение сообщения из очереди newOrderQueueToRestaurant о новом заказе и
     * зменение статуса заказа.
     *
     * @param response ответ заказа
     */
    @RabbitListener(queues = "newOrderQueueToRestaurant")
    public void processQueueCreateOrder(String response) throws IOException {
        CreateOrderResponse createOrderResponse = objectMapper.readValue(response, CreateOrderResponse.class);
        log.info("Получен новый заказ {}, необходимо принять его или отклонить ", createOrderResponse);

    }

}

