package ru.liga.service.rabbitMQ;

import java.util.UUID;
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
    public void sendMessageOrder(String massage, UUID orderId) {
        rabbitMQProducerService.sendMessageOrder(massage, orderId, "to_notification");
    }

    /**
     * Метод  для отправки сообщения о создании нового заказа с конкретным routing key.
     *
     * @param id              идентификатор заказа
     * @param addressCustomer адрес заказчика
     */

    public void sendCourierSearch(UUID id, String addressCustomer) {
        rabbitMQProducerService.sendMessageSearch(id, addressCustomer, "to_notification");
    }

}
