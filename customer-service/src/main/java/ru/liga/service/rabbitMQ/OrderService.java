package ru.liga.service.rabbitMQ;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.request.CreateOrderRequest;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final RabbitMQProducerServiceImpl rabbitMQProducerService;

    public void createOrder(CreateOrderRequest request) {
        rabbitMQProducerService.sendCreateRequest(request, "create_order_to_order");
    }
}
