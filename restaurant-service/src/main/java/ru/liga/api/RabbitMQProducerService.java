package ru.liga.api;

import ru.liga.dto.response.CreateOrderResponse;

/**
 * Интерфейс отправителя сообщения.
 */
public interface RabbitMQProducerService {

    /**
     * Метод отправки сообщения о поиске курьера.
     *
     * @param createOrderResponse ответ заказа
     */
    void sendMessageSearch(CreateOrderResponse createOrderResponse, String routingKey);

    /**
     * Метод отправки сообщения об обновлении статуса заказа.
     *
     * @param orderId идентификатор заказа
     */
    void sendMessageUpdate(Long orderId, String routingKey);
}
