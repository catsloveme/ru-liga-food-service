package ru.liga.service.rabbitMQ;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.liga.api.RabbitMQProducerService;

/**
 * Класс по созданию сообщения, используя RabbitTemplate.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQProducerServiceImpl implements RabbitMQProducerService {
    private final RabbitTemplate rabbitTemplate;

    /**
     * Отправка сообщения через RabbitTemplate.
     * @param orderId идентификатор заказа
     * @param routingKey ключ для определения очереди
     */
    public void sendMessageCreate(Long orderId, String routingKey) {
        rabbitTemplate.convertAndSend("directExchange", routingKey, orderId.toString());
        log.info("Message about creating a new order has been sent to the Notification service");
    }
}
