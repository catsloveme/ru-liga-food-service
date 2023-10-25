package ru.liga.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.liga.api.OrderService;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CreateOrderResponse;
import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class QueueListener {
    private final OrderService orderService;
    @RabbitListener(queues = "createOrderQueue")
    public void processQueueCreateOrder(String request) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CreateOrderRequest createOrderRequest = objectMapper.readValue(request, CreateOrderRequest.class);
        CreateOrderResponse response = orderService.addOrder(createOrderRequest);
        log.info("Received from createOrderQueue : " +  response);
    }

}

