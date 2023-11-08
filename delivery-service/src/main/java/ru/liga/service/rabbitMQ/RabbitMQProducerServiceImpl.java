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
     * @param courierId  идентификатор курьера
     * @param routingKey ключ для определения очереди
     */
    public void sendMessage(UUID orderId, UUID courierId, String routingKey) {

        ResponseAndKey responseAndKey = ResponseAndKey
            .builder()
            .id(orderId)
            .response(String.valueOf(courierId))
            .key("kitchen_about_courier")
            .build();

        String jsonRequest;
        try {
            jsonRequest = mapper.writeValueAsString(responseAndKey);
        } catch (JsonProcessingException e) {
            log.info("JsonProcessingException");
            throw new RuntimeException(e);
        }
        rabbitTemplate.convertAndSend("directExchange", routingKey, jsonRequest);
        log.info("Сообщение о результате поиска курьеров отправлено в ресторан через notification сервис");
    }

    /**
     * Отправка сообщения об успешной доставке заказа.
     *
     * @param orderId    идентификатор заказа
     * @param routingKey ключ для определения очереди
     */
    public void sendMessageFinish(UUID orderId, String message, String routingKey) {
        ResponseAndKey responseAndKey = ResponseAndKey
            .builder()
            .id(orderId)
            .response(message)
            .key("kitchen")
            .build();
        String jsonRequest;
        try {
            jsonRequest = mapper.writeValueAsString(responseAndKey);
        } catch (JsonProcessingException e) {
            log.info("JsonProcessingException");
            throw new RuntimeException(e);
        }

        rabbitTemplate.convertAndSend("directExchange", routingKey, jsonRequest);
    }

}
