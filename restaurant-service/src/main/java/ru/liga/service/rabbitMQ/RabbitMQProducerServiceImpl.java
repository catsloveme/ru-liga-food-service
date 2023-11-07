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

    /**
     * Отправка сообщения через RabbitTemplate.
     *
     * @param response ответ созданного заказа
     * @param routingKey          ключ для определения очереди
     */
//    public void sendMessageSearch(CreateOrderResponse response, String routingKey) {
//        ResponseAndKey responseAndKey = ResponseAndKey
//            .builder()
//            .response(response)
//            .key("order")
//            .build();
//
//        String jsonRequest;
//        try {
//            jsonRequest = mapper.writeValueAsString(responseAndKey);
//        } catch (JsonProcessingException e) {
//            log.info("JsonProcessingException");
//            throw new RuntimeException(e);
//        }
//        rabbitTemplate.convertAndSend("directExchange", routingKey, jsonRequest);
//        log.info("Message about search a courier has been sanded to the Notification service");
//    }

    /**
     * Отправка уведомления заказчику.
     *
     * @param orderId    идентификатор заказа
     * @param routingKey ключ для определения очереди
     */
    public void sendMessageOrder(String message, Long orderId, String routingKey) {
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
        log.info("Message about search a courier has been sanded to the Notification service");
    }
}
