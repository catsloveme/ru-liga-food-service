package ru.liga.service.rabbitMQ;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.AbstractMap;
import java.util.UUID;
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
    private final ObjectMapper mapper;


    /**
     * Отправка сообщения через RabbitTemplate.
     *
     * @param orderId    идентификатор заказа.
     * @param routingKey ключ для определения очереди.
     */
    public void sendIdAndMessage(UUID orderId, String message, String routingKey) {
        AbstractMap.SimpleEntry<UUID, String> pair = new AbstractMap.SimpleEntry<>(orderId, message);
        String jsonRequest;
        try {
            jsonRequest = mapper.writeValueAsString(pair);
        } catch (JsonProcessingException e) {
            log.info("JsonProcessingException");
            throw new RuntimeException(e);
        }
        rabbitTemplate.convertAndSend("directExchange", routingKey, jsonRequest);
    }

    /**
     * Отправка сообщения в delivery о поиске курьера.
     *
     * @param orderId           идентификатор заказа
     * @param addressRestaurant адрес заказчика
     * @param routingKey        ключ для определения очереди.
     */
    public void sendCourierSearch(UUID orderId, String addressRestaurant, String routingKey) {
        AbstractMap.SimpleEntry<UUID, String> pair = new AbstractMap.SimpleEntry<>(orderId, addressRestaurant);
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
    public void sendMessageAboutSearchingCourier(UUID orderId, UUID courierId, String routingKey) {
        AbstractMap.SimpleEntry<UUID, UUID> pair = new AbstractMap.SimpleEntry<>(orderId, courierId);
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
