package ru.liga.api;

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

    void sendMessageCreate(Long orderId, String message, String routingKey);

}
