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
     * @param orderId идентификатор заказа.
     * @param message сообщение.
     */
    public void sendCreateOrder(UUID orderId, String message) {
        rabbitMQProducerService.sendMessageCreate(orderId, message, "to_notification");
    }

}
