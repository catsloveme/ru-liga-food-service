package ru.liga.api;

public interface RabbitMQProducerService {

    void sendMessageCreate(Long orderId, String routingKey);

}
