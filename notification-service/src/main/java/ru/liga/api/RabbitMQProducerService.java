package ru.liga.api;

/**
 * Интерфейс отправителя сообщения.
 */
public interface RabbitMQProducerService {

    /**
     * Метод отправки сообщения о создании нового заказа.
     * @param orderId
     * @param routingKey
     */
    void sendMessageCreate(Long orderId, String routingKey);

}
