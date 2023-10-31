package ru.liga.service.rabbitMQ;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.request.CreateOrderRequest;

/**
 * Класс для отправки сообщения с использованием конкретного routing key.
 */
@Service
@RequiredArgsConstructor
public class OrderService {
    private final RabbitMQProducerServiceImpl rabbitMQProducerService;

    /**
     * Метод  для отправки сообщения о создании нового заказа с конкретным routing key.
     * @param request
     */
    public void createOrder(CreateOrderRequest request) {
        rabbitMQProducerService.sendCreateRequest(request, "create_order_to_order");
    }
}
