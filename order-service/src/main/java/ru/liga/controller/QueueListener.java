package ru.liga.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.liga.api.OrderService;
import java.util.UUID;

/**
 * Класс получателя сообщений.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class QueueListener {
    private final ObjectMapper objectMapper;

    /**
     * Метод, отвечающий за получение уведомлений заказчика.
     *
     * @param pairMessage пара, состоящая из идентификатора заказа и сообщения заказчику
     */
    @RabbitListener(queues = "toOrder")
    public void updateStatusFromRestaurant(String pairMessage) throws JsonProcessingException {
        String strWithoutBrackets = pairMessage.substring(1, pairMessage.length() - 1);
        String[] arrayOrderIdAndCourierId = strWithoutBrackets.split(":");

        UUID orderId = objectMapper.readValue(arrayOrderIdAndCourierId[0], UUID.class);
        System.out.println(pairMessage);
        String message = arrayOrderIdAndCourierId[1];

        log.info(message, orderId);
    }

}

