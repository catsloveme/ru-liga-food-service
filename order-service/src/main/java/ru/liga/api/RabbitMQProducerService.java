package ru.liga.api;

import ru.liga.dto.response.CreateOrderResponse;

/**
 * Интерфейс отправителя сообщения.
 */
public interface RabbitMQProducerService {

    /**
     * Метод отправки сообщения о создании нового заказа.
     *
     * @param response ответ заказа
     */
    void sendMessageCreate(CreateOrderResponse response, String routingKey);

}
