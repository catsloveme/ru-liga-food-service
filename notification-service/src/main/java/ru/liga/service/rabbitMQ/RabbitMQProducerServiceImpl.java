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
     * @param response   ответ созданного заказа.
     * @param routingKey ключ для определения очереди.
     */
    public void sendCourierSearch(CreateOrderResponse response, String routingKey) {
        String jsonRequest;
        try {
            jsonRequest = mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            log.info("JsonProcessingException");
            throw new RuntimeException(e);
        }
        rabbitTemplate.convertAndSend("directExchange", routingKey, jsonRequest);
        log.info("Message about searching a courier has been sent to the Courier service");
    }

    /**
     * Отправка сообщения через RabbitTemplate.
     *
     * @param orderId    идентификатор заказа.
     * @param routingKey ключ для определения очереди.
     */
    public void sendUpdateDeliveryPicking(Long orderId, Long courierId, String routingKey) {
        AbstractMap.SimpleEntry<Long, Long> pair = new AbstractMap.SimpleEntry<>(orderId, courierId);
        rabbitTemplate.convertAndSend("directExchange", routingKey, pair.toString());
        log.info("Message about update order status - DeliveryPicking -  has been sent to the Courier service");
    }

    /**
     * Отправка сообщения через RabbitTemplate.
     *
     * @param orderId    идентификатор заказа.
     * @param routingKey ключ для определения очереди.
     */
    public void sendUpdateKitchenAccepted(Long orderId, String routingKey) {
        rabbitTemplate.convertAndSend("directExchange", routingKey, orderId.toString());
        log.info("Message about update order status - KitchenAccepted - has been sent to the Courier service");
    }
}
