package ru.liga.service.rabbitMQ;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourierService {
    private final RabbitMQProducerServiceImpl rabbitMQProducerService;

    public void sendMessageSearch(Long orderId) {
        rabbitMQProducerService.sendCourierSearch(orderId, "courier_search_to_courier");
    }

}
