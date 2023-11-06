package ru.liga.service.rabbitMQ;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.response.CreateOrderResponse;

/**
 * Класс для отправки сообщения с использованием конкретного routing key.
 */
@Service
@RequiredArgsConstructor
public class NotificationService {
    private final RabbitMQProducerServiceImpl rabbitMQProducerService;

    /**
     * Метод  для отправки сообщения о создании нового заказа с конкретным routing key.
     *
     * @param createOrderResponse ответ созданного заказа.
     */
    public void sendCourierSearch(CreateOrderResponse createOrderResponse) {
        rabbitMQProducerService.sendMessageSearch(createOrderResponse, "courier_search_to_notification");
    }

    public void updateOrderStatus(Long orderId) {
        rabbitMQProducerService.sendMessageUpdate(orderId, "update_order_status_to_notification");
    }

}
