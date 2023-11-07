package ru.liga.service.rabbitMQ;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Класс для отправки сообщения с использованием конкретного routing key.
 */
@Service
@RequiredArgsConstructor
public class OrderService {
    private final RabbitMQProducerServiceImpl rabbitMQProducerService;

//    /**
//     * Метод  для отправки сообщения о поиске курьера с конкретным routing key.
//     *
//     * @param orderId идентификатор заказа
//     */
//    public void sendMessageUpdateStatusDeliveryPicking(Long orderId, Long courierId) {
//        rabbitMQProducerService.sendUpdateDeliveryPicking(orderId, courierId, "update_status_order_delivery_picking");
//    }

    public void sendMessageOrder(Long orderId, String message) {
        rabbitMQProducerService.sendMessageOrder(orderId, message, "message_to_order");
    }

}
