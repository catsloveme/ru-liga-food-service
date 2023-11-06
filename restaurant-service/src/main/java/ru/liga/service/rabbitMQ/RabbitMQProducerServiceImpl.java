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
     * @param createOrderResponse ответ созданного заказа
     * @param routingKey          ключ для определения очереди
     */
    public void sendMessageSearch(CreateOrderResponse createOrderResponse, String routingKey) {
        String jsonRequest;
        try {
            jsonRequest = mapper.writeValueAsString(createOrderResponse);
        } catch (JsonProcessingException e) {
            log.info("JsonProcessingException");
            throw new RuntimeException(e);
        }
        rabbitTemplate.convertAndSend("directExchange", routingKey, jsonRequest);
        log.info("Message about search a courier has been sanded to the Notification service");
    }

    /**
     * Отправка сообщения через RabbitTemplate.
     *
     * @param orderId    идентификатор заказа
     * @param routingKey ключ для определения очереди
     */
    public void sendMessageUpdate(Long orderId, String routingKey) {

        rabbitTemplate.convertAndSend("directExchange", routingKey, orderId);
        log.info("Message about search a courier has been sanded to the Notification service");
    }
}
