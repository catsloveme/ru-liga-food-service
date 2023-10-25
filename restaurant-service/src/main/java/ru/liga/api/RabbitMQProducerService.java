package ru.liga.api;

public interface RabbitMQProducerService {

    void sendMessageSearch(Long orderId, String routingKey);

}
