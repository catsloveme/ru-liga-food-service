package ru.liga.service.rabbitMQ;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.liga.api.RabbitMQProducerService;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.enums.StatusOrder;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQProducerServiceImpl implements RabbitMQProducerService {
    private final RabbitTemplate rabbitTemplate;

    public void sendStatus(StatusOrder status, String routingKey) {
        rabbitTemplate.convertAndSend("directExchange", routingKey, status);
        log.info("Status has been sent");
    }
    public void sendCreateRequest(CreateOrderRequest request, String routingKey) {
        rabbitTemplate.convertAndSend("directExchange", routingKey, request);
        log.info("Request has been sent");
    }
}
