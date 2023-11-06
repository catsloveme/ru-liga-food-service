package ru.liga.service.rabbitMQ;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.liga.api.RabbitMQProducerService;
import ru.liga.dto.response.CreateOrderResponse;

/**
 * Класс по созданию сообщения, используя RabbitTemplate.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQProducerServiceImpl implements RabbitMQProducerService {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper;

    /**
     * Отправка сообщения через RabbitTemplate.
     *
     * @param response   ответ создания заказа
     * @param routingKey ключ для определения очереди
     */
    public void sendMessageCreate(CreateOrderResponse response, String routingKey) {
        String jsonRequest;
        try {
            jsonRequest = mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            log.info("JsonProcessingException");
            throw new RuntimeException(e);
        }
        rabbitTemplate.convertAndSend("directExchange", routingKey, jsonRequest);
        log.info("Message about creating a new order has been sent to the Notification service");
    }

}
