package ru.liga.service.rabbitMQ;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final RabbitMQProducerServiceImpl rabbitMQProducerService;


    public void sendCourierSearch(Long orderId) {
        rabbitMQProducerService.sendMessageSearch(orderId, "courier_search_to_notification");
    }

}
