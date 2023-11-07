package ru.liga.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.liga.api.OrderService;
import ru.liga.dto.ResponseAndKey;

/**
 * Класс получателя сообщений.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class QueueListener {
    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    /**
     * Метод, отвечающий за получение уведомлений заказчика.
     *
     * @param responseMessage сообщение заказчику
     */
    @RabbitListener(queues = "toOrder")
    public void updateStatusFromRestaurant(String responseMessage) throws JsonProcessingException {
        ResponseAndKey responseAndKey = objectMapper.readValue(responseMessage, ResponseAndKey.class);
        Long idOrder = responseAndKey.getId();
        String message = responseAndKey.getResponse();
        log.info(message, idOrder);
    }
//
//    @RabbitListener(queues = "updateStatusOrderDelivery")
//    public void updateStatusFromDelivery(String pairMessage) {
//        String[] arrayOrderIdAndCourierId = pairMessage.split("=");
//        Long orderId = Long.parseLong(arrayOrderIdAndCourierId[0]);
//        Long courierId = Long.parseLong(arrayOrderIdAndCourierId[1]);
//        orderService.updateOrderStatus(StatusOrder.DELIVERY_PICKING, orderId);
//        orderService.updateCourierId(orderId, courierId);
//    }
}

