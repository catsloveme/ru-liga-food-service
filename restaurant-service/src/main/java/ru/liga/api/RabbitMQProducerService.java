package ru.liga.api;

/**
 * Интерфейс отправителя сообщения.
 */
public interface RabbitMQProducerService {

    /**
     * Метод отправки сообщения о поиске курьера.
     *
     * @param orderId идентификатор заказа
     */
    void sendMessageSearch(Long orderId, String routingKey);

}
