package ru.liga.api;

import java.util.List;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.dto.response.OrderResponse;
import ru.liga.enums.StatusOrder;

/**
 * Интерфейс описания api сервиса Order.
 */
public interface OrderService {
    /**
     * Поиск всех заказов.
     *
     * @return список ответов заказов
     */
    List<OrderResponse> findAllOrders();

    /**
     * Поиск заказа по его id.
     *
     * @param orderId идентификатор заказа
     * @return ответ заказа
     */
    OrderResponse findOrderById(Long orderId);

    /**
     * Поиск истории заказов по id заказчика.
     *
     * @param customerId идентификатор заказчика
     * @return ответ заказа
     */
    List<OrderResponse> findOrdersByCustomerId(Long customerId);

    /**
     * Создание заказа.
     *
     * @param requestCreatingOrder данные для запроса на создание заказа
     * @return ответ создания заказа
     */
    CreateOrderResponse addOrder(CreateOrderRequest requestCreatingOrder);

    /**
     * Обновление id курьера по id заказа.
     *
     * @param courierId идентификатор курьера
     * @param orderId   идентификатор заказа
     */
    void updateCourierId(Long courierId, Long orderId);

    /**
     * Обновление статуса заказа по его id.
     *
     * @param status  статус заказа
     * @param orderId идентификатор заказа
     */
    void updateOrderStatus(StatusOrder status, Long orderId);
}
