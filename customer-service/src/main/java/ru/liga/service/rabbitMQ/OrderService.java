package ru.liga.service.rabbitMQ;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.enums.StatusOrder;
import ru.liga.enums.StatusRestaurant;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final RabbitMQProducerServiceImpl rabbitMQProducerService;

    private final ObjectMapper objectMapper;

    public void createOrder(CreateOrderRequest request) {
        rabbitMQProducerService.sendCreateRequest(request, "create_order");
    }

    public void updateStatus(StatusOrder status) {
        if (status.equals(StatusOrder.CUSTOMER_PAID)) {
            rabbitMQProducerService.sendStatus(status, "paid_order");
        } else if (status.equals(StatusOrder.CUSTOMER_CANCELLED)) {
            rabbitMQProducerService.sendStatus(status, "cancel_order");
        }
    }
}
