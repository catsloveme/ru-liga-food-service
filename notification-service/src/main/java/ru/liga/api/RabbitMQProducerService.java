package ru.liga.api;

import ru.liga.dto.CreateOrderResponse;

/**
 * Интерфейс отправителя сообщения.
 */
public interface RabbitMQProducerService {

    /**
     * Метод отправки сообщения о создании нового заказа.
     *
     * @param response запрос для создания заказа
     */
    void sendMessageCreate(CreateOrderResponse response, String routingKey);

    /**
     * Отправка сообщения через RabbitTemplate.
     *
     * @param orderId    идентификатор заказа.
     * @param routingKey ключ для определения очереди.
     */
    void sendUpdateDeliveryPicking(Long orderId, Long courierId, String routingKey);

    /**
     * Отправка сообщения через RabbitTemplate.
     *
     * @param orderId    идентификатор заказа.
     * @param routingKey ключ для определения очереди.
     */
    void sendUpdateKitchenAccepted(Long orderId, String routingKey);

    /**
     * Отправка сообщения через RabbitTemplate.
     *
     * @param response   ответ созданного заказа.
     * @param routingKey ключ для определения очереди.
     */
    void sendCourierSearch(CreateOrderResponse response, String routingKey);
}
