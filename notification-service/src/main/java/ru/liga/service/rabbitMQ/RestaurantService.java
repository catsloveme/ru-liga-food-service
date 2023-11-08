package ru.liga.service.rabbitMQ;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * Класс для отправки сообщения с использованием конкретного routing key.
 */
@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RabbitMQProducerServiceImpl rabbitMQProducerService;

    /**
     * Метод  для отправки сообщения о создании нового заказа с конкретным routing key.
     *
     * @param orderId идентификатор заказа.
     * @param message сообщение.
     */
    public void sendMessageCreate(UUID orderId, String message) {
        rabbitMQProducerService.sendIdAndMessage(orderId, message, "new_order_to_restaurant");
    }

    public void sendMessageAboutSearchingCourier(UUID orderId, UUID idCourier) {
        rabbitMQProducerService.sendMessageAboutSearchingCourier(orderId, idCourier, "kitchen_about_courier");
    }

    public void sendMessageAboutDelivered(UUID orderId, String message) {
        rabbitMQProducerService.sendIdAndMessage(orderId, message, "kitchen_about_delivery");
    }

}
