package ru.liga.service.rabbitMQ;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final RabbitMQProducerServiceImpl rabbitMQProducerService;

    public void sendCreateOrder(Long orderId) {
        rabbitMQProducerService.sendMessageCreate(orderId, "new_order_to_notification");
    }

}
