package ru.liga.service.rabbitMQ;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
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

    /**
     * Отправка сообщения через RabbitTemplate.
     *
     * @param id              идентификатор заказа
     * @param addressCustomer адрес заказчика
     * @param routingKey      ключ для определения очереди
     */
    public void sendMessageSearch(UUID id, String addressCustomer, String routingKey) {
        String jsonResponse;
        try {
            jsonResponse = mapper.writeValueAsString(addressCustomer);//проверить может строка не требует модификации
        } catch (JsonProcessingException e) {
            log.info("JsonProcessingException");
            throw new RuntimeException(e);
        }
        ResponseAndKey responseAndKey = ResponseAndKey
            .builder()
            .response(jsonResponse)
            .id(id)
            .key("courier")
            .build();

        String jsonRequest;
        try {
            jsonRequest = mapper.writeValueAsString(responseAndKey);
        } catch (JsonProcessingException e) {
            log.info("JsonProcessingException");
            throw new RuntimeException(e);
        }
        rabbitTemplate.convertAndSend("directExchange", routingKey, jsonRequest);
        log.info("Сообщение о поиске курьера отправлено в сервис доставки через notification сервис");
    }

    /**
     * Отправка уведомления заказчику.
     *
     * @param orderId    идентификатор заказа
     * @param routingKey ключ для определения очереди
     */
    public void sendMessageOrder(String message, UUID orderId, String routingKey) {
        String jsonResponse;
        try {
            jsonResponse = mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            log.info("JsonProcessingException");
            throw new RuntimeException(e);
        }
        ResponseAndKey responseAndKey = ResponseAndKey
            .builder()
            .id(orderId)
            .response(jsonResponse)
            .key("order")
            .build();

        String jsonRequest;
        try {
            jsonRequest = mapper.writeValueAsString(responseAndKey);
        } catch (JsonProcessingException e) {
            log.info("JsonProcessingException");
            throw new RuntimeException(e);
        }
        rabbitTemplate.convertAndSend("directExchange", routingKey, jsonRequest);
        log.info("Уведомление отправлено заказчику");
    }
}
