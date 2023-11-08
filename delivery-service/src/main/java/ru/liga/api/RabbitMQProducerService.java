package ru.liga.api;

import java.util.UUID;

/**
 * Интерфейс отправителя сообщения.
 */
public interface RabbitMQProducerService {

    /**
     * Метод отправки сообщения о создании нового заказа.
     *
     * @param courierId идентификатор курьера
     */
    void sendMessage(UUID orderId, UUID courierId, String routingKey);

}
