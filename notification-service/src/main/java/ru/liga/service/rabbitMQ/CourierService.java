package ru.liga.service.rabbitMQ;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Класс для отправки сообщения с использованием конкретного routing key.
 */
@Service
@RequiredArgsConstructor
public class CourierService {
    private final RabbitMQProducerServiceImpl rabbitMQProducerService;

    /**
     * Метод  для отправки сообщения о поиске курьера с конкретным routing key.
     * @param orderId идентификатор заказа
     * @param addressRestaurant адрес ресторана
     */

    public void sendMessageSearch(Long orderId, String addressRestaurant) {
        rabbitMQProducerService.sendCourierSearch(orderId, addressRestaurant, "courier_search_to_courier");
    }

}
