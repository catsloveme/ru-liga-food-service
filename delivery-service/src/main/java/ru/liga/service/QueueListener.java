package ru.liga.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class QueueListener {
    @RabbitListener(queues = "courier_search")
    public void getNewOrder(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        MessageModel messageModel = objectMapper.readValue(message, MessageModel.class);
        log.info("Received from myQueue1 : " +  messageModel.getModelInfo());
        log.info("Работа в it");
    }
}
