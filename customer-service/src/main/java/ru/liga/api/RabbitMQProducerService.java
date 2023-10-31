package ru.liga.api;

import ru.liga.dto.request.CreateOrderRequest;

/**
 * Интерфейс отправителя сообщения.
 */
public interface RabbitMQProducerService {

    /**
     * Метод отправки сообщения о создании нового заказа.
     * @param request CreateOrderRequest
     * @param routingKey
     */
    void sendCreateRequest(CreateOrderRequest request, String routingKey);

}
