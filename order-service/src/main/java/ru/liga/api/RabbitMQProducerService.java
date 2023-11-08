package ru.liga.api;

import java.util.UUID;

/**
 * Интерфейс отправителя сообщения.
 */
public interface RabbitMQProducerService {

    /**
     * Метод отправки сообщения о создании нового заказа.
     *
     * @param orderId идентификатор заказа
     * @param message сообщение о создании заказа
     */

    void sendMessageCreate(UUID orderId, String message, String routingKey);

}
