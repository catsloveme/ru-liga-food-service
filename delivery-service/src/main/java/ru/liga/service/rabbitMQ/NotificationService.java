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
     * Метод для отправки сообщения о создании нового заказа с конкретным routing key.
     *
     * @param courierId идентификатор курьера
     */
    public void sendMessage(UUID orderId, UUID courierId) {
        rabbitMQProducerService.sendMessage(orderId, courierId, "to_notification");
    }

    /**
     * Метод для отправки сообщения об успешной доставке заказа.
     *
     * @param orderId идентификатор заказа
     */
    public void sendMessageFinish(UUID orderId, String message) {
        rabbitMQProducerService.sendMessageFinish(orderId, message, "to_notification");
    }

}
