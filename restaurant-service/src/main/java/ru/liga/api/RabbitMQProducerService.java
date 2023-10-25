package ru.liga.api;

public interface RabbitMQProducerService {

    void sendMessage(String message, String routingKey);

}
