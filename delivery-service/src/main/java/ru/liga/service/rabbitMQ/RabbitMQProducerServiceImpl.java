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
     *
     * @param courierId  идентификатор курьера
     * @param routingKey ключ для определения очереди
     */
    public void sendMessageUpdate(Long orderId, Long courierId, String routingKey) {

        rabbitTemplate.convertAndSend("directExchange", routingKey, courierId.toString());
        log.info("Message about courier is found has been sent to the Notification service");
    }

}
