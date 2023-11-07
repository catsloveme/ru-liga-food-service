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
     * @param courierId идентификатор курьера
     */
    public void sendMessageUpdate(Long orderId, Long courierId) {
        rabbitMQProducerService.sendMessageUpdate(orderId, courierId, "courier_is_found");
    }

}
