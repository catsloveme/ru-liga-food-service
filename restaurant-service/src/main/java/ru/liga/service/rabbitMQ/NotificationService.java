package ru.liga.service.rabbitMQ;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Класс для отправки сообщения с использованием конкретного routing key.
 */
@Service
@RequiredArgsConstructor
public class NotificationService {
    private final RabbitMQProducerServiceImpl rabbitMQProducerService;

    /**
     * Метод  для отправки сообщения о создании нового заказа с конкретным routing key.
     *
     * @param massage уведомление для заказчика
     * @param orderId идентификатор заказа
     */
    public void sendMessageOrder(String massage, Long orderId) {
        rabbitMQProducerService.sendMessageOrder(massage, orderId, "to_notification");
    }
//    /**
//     * Метод  для отправки сообщения о создании нового заказа с конкретным routing key.
//     *
//     * @param createOrderResponse ответ созданного заказа.
//     */
//    public void sendCourierSearch(CreateOrderResponse createOrderResponse) {
//        rabbitMQProducerService.sendMessageSearch(createOrderResponse, "to_notification");
//    }

}
