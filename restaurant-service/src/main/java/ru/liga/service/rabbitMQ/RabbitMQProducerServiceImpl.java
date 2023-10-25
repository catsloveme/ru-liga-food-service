package ru.liga.service.rabbitMQ;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.liga.api.RabbitMQProducerService;
@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQProducerServiceImpl implements RabbitMQProducerService {
    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(String message, String routingKey) {
        rabbitTemplate.convertAndSend("directExchange", routingKey, message);
        log.info("Message has been sanded");
    }
}
