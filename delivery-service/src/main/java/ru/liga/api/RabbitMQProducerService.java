package ru.liga.api;

/**
 * Интерфейс отправителя сообщения.
 */
public interface RabbitMQProducerService {

    /**
     * Метод отправки сообщения о создании нового заказа.
     *
     * @param courierId идентификатор курьера
     */
    void sendMessageUpdate(Long orderId, Long courierId, String routingKey);

}
