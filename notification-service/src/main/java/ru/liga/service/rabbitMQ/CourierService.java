package ru.liga.service.rabbitMQ;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.CreateOrderResponse;

/**
 * Класс для отправки сообщения с использованием конкретного routing key.
 */
@Service
@RequiredArgsConstructor
public class CourierService {
    private final RabbitMQProducerServiceImpl rabbitMQProducerService;

    /**
     * Метод  для отправки сообщения о поиске курьера с конкретным routing key.
     *
     * @param response ответ созданного заказа
     */
    public void sendMessageSearch(CreateOrderResponse response) {
        rabbitMQProducerService.sendCourierSearch(response, "courier_search_to_courier");
    }

}
