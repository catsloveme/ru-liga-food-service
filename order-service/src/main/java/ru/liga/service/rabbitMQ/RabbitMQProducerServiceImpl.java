package ru.liga.service.rabbitMQ;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.liga.api.RabbitMQProducerService;
import ru.liga.dto.ResponseAndKey;

/**
 * Класс по созданию сообщения, используя RabbitTemplate.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQProducerServiceImpl implements RabbitMQProducerService {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper;
    private final static String KEY = "kitchen";

    /**
     * Отправка сообщения о новом заказе ресторану с ключем - kitchen .
     *
     * @param orderId    идентификатор заказа
     * @param routingKey ключ для определения очереди
     */
    public void sendMessageCreate(Long orderId, String message, String routingKey) {
        ResponseAndKey responseAndKey = ResponseAndKey
            .builder()
            .id(orderId)
            .response(message)
            .key(KEY)
            .build();

        String jsonRequest;
        try {
            jsonRequest = mapper.writeValueAsString(responseAndKey);
        } catch (JsonProcessingException e) {
            log.info("JsonProcessingException");
            throw new RuntimeException(e);
        }
        rabbitTemplate.convertAndSend("directExchange", routingKey, jsonRequest);
        log.info("Сообщение о создании нового заказа отправлено в ресторан через notification сервис");
    }

}
