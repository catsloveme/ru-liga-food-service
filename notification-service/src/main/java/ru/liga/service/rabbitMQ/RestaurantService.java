package ru.liga.service.rabbitMQ;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RabbitMQProducerServiceImpl rabbitMQProducerService;

    public void sendMessageCreate(Long orderId) {
        rabbitMQProducerService.sendMessageCreate(orderId, "new_order_to_restaurant");
    }

}
