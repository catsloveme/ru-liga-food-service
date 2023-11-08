package ru.liga.service.rabbitMQ;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.CreateOrderResponse;

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
     * @param response ответ создания заказа.
     */
    public void sendMessageCreate(CreateOrderResponse response) {
        rabbitMQProducerService.sendMessageCreate(response, "new_order_to_restaurant");
    }

    public void sendMessageAboutSearchingCourier(Long orderId, Long idCourier) {
        rabbitMQProducerService.sendMessageAboutSearchingCourier(orderId, idCourier, "kitchen_about_courier");
    }

}
