package ru.liga.service.rabbitMQ;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.AbstractMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.liga.api.RabbitMQProducerService;
import ru.liga.dto.CreateOrderResponse;
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
     * @param response   ответ созадния заказа
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
        log.info("Message about creating a new order has been sent to the Restaurant service");
    }


    /**
     * Отправка сообщения через RabbitTemplate.
     *
     * @param orderId    идентификатор заказа.
     * @param routingKey ключ для определения очереди.
     */
    public void sendMessageOrder(Long orderId, String message, String routingKey) {
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

    /**
     * Отправка сообщения в delivery о поиске курьера.
     *
     * @param orderId           идентификатор заказа
     * @param addressRestaurant адрес заказчика
     * @param routingKey        ключ для определения очереди.
     */
    public void sendCourierSearch(Long orderId, String addressRestaurant, String routingKey) {
        AbstractMap.SimpleEntry<Long, String> pair = new AbstractMap.SimpleEntry<>(orderId, addressRestaurant);
        String jsonRequest;
        try {
            jsonRequest = mapper.writeValueAsString(pair);
        } catch (JsonProcessingException e) {
            log.info("JsonProcessingException");
            throw new RuntimeException(e);
        }
        rabbitTemplate.convertAndSend("directExchange", routingKey, jsonRequest);
        log.info("Сообщение о поиске курьера было отправлено в сервис доставки");
    }

    /**
     * Отправка сообщения через RabbitTemplate.
     *
     * @param orderId    идентификатор заказа.
     * @param routingKey ключ для определения очереди.
     */
    public void sendMessageAboutSearchingCourier(Long orderId, Long courierId, String routingKey) {
        AbstractMap.SimpleEntry<Long, Long> pair = new AbstractMap.SimpleEntry<>(orderId, courierId);
        String jsonRequest;
        try {
            jsonRequest = mapper.writeValueAsString(pair);
        } catch (JsonProcessingException e) {
            log.info("JsonProcessingException");
            throw new RuntimeException(e);
        }

        rabbitTemplate.convertAndSend("directExchange", routingKey, jsonRequest);
        log.info("Сообщение о результате поиска курьеров отправлено в ресторан");
    }
}
