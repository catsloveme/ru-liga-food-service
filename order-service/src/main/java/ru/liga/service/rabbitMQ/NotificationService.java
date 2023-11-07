package ru.liga.service.rabbitMQ;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.response.CreateOrderResponse;

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
     * @param response ответ создания заказа
     */
    public void sendCreateOrder(CreateOrderResponse response) {
        rabbitMQProducerService.sendMessageCreate(response, "to_notification");
    }

}
