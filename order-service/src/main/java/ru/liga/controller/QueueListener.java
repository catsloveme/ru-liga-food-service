package ru.liga.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.liga.api.OrderService;
import ru.liga.enums.StatusOrder;

@Service
@Slf4j
@RequiredArgsConstructor
public class QueueListener {
    private final OrderService orderService;
    @RabbitListener(queues = "createOrderQueue")
    public void processQueueCreateOrder(StatusOrder status) throws JsonProcessingException {
       log.info("Received from createOrderQueue : " +  status.name());

    }

}
//    Queue queueDirectCreateOrder = new Queue("create_order", false);
//    Queue queueDirectPaidOrder = new Queue("paid_order", false);
//    Queue queueDirectCancelOrder = new Queue("cancel_order", false);
//    DirectExchange directExchange = new DirectExchange("directExchange");
//createOrderQueue", false);
//        Queue queueDirectPaidOrder = new Queue("paidOrderQueue", false);
//        Queue queueDirectCancelOrder = new Queue("cancelOrderQueue", false);
//        DirectExchange directExchange = new DirectExchange("directExchange");
