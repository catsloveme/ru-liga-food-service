package ru.liga.api;

import java.util.UUID;

/**
 * Интерфейс отправителя сообщения.
 */
public interface RabbitMQProducerService {

//    /**
//     * Метод отправки сообщения о поиске курьера.
//     *
//     * @param createOrderResponse ответ заказа
//     */
//    void sendMessageSearch(CreateOrderResponse createOrderResponse, String routingKey);

    /**
     * Метод отправки сообщения об обновлении статуса заказа.
     *
     * @param orderId идентификатор заказа
     */
    void sendMessageOrder(String message, UUID orderId, String routingKey);
}
