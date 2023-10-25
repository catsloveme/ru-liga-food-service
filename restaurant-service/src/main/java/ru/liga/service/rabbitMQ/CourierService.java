package ru.liga.service.rabbitMQ;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourierService {
    private final RabbitMQProducerServiceImpl rabbitMQProducerService;

    private final ObjectMapper objectMapper;

    public void sendCourierSearch(Long orderId) {
        rabbitMQProducerService.sendMessageSearch(orderId, "courier_search");
    }

}
