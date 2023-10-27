package ru.liga.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.liga.api.RestaurantService;
import ru.liga.clients.OrderFeign;
import ru.liga.enums.StatusOrder;
import ru.liga.enums.StatusRestaurant;
import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class QueueListener {
    private final OrderFeign orderFeign;
    private final RestaurantService jpaRestaurantService;
    @RabbitListener(queues = "newOrderQueueToRestaurant")
    public void processQueueCreateOrder(String orderId) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Long id = objectMapper.readValue(orderId, Long.class);
        orderFeign.updateOrderStatus(id, StatusOrder.KITCHEN_ACCEPTED);
        jpaRestaurantService.changeOrderStatusById(StatusRestaurant.KITCHEN_ACCEPTED, id);
    }

}

