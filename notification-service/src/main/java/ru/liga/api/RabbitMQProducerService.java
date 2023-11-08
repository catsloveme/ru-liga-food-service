package ru.liga.api;

/**
 * Интерфейс отправителя сообщения.
 */
public interface RabbitMQProducerService {

    /**
     * Отправка сообщения через RabbitTemplate.
     *
     * @param orderId    идентификатор заказа.
     * @param routingKey ключ для определения очереди.
     */
    void sendIdAndMessage(Long orderId, String message, String routingKey);

    /**
     * Отправка сообщения в delivery о поиске курьера.
     *
     * @param orderId           идентификатор заказа
     * @param addressRestaurant адрес заказчика
     * @param routingKey        ключ для определения очереди.
     */
    void sendCourierSearch(Long orderId, String addressRestaurant, String routingKey);

    /**
     * Отправка сообщения через RabbitTemplate.
     *
     * @param orderId    идентификатор заказа.
     * @param routingKey ключ для определения очереди.
     */
    void sendMessageAboutSearchingCourier(Long orderId, Long courierId, String routingKey);
}
