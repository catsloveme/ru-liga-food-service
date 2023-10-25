package ru.liga.api;

import ru.liga.dto.request.CreateOrderRequest;

public interface RabbitMQProducerService {

    void sendCreateRequest(CreateOrderRequest request, String routingKey);

}
