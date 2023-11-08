package ru.liga.service.rabbitMQ;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * Класс для отправки сообщения с использованием конкретного routing key.
 */
@Service
@RequiredArgsConstructor
public class OrderService {
    private final RabbitMQProducerServiceImpl rabbitMQProducerService;



    public void sendMessageOrder(UUID orderId, String message) {
        rabbitMQProducerService.sendIdAndMessage(orderId, message, "message_to_order");
    }

}
