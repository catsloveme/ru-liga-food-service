package ru.liga.api;

import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.dto.response.OrderResponse;
import ru.liga.enums.StatusOrder;

import java.util.List;

/**
 * Интерфейс описания api сервиса Order.
 */
public interface OrderService {
    /**
     * Поиск всех заказов.
     * @return список ответов заказов
     */
    List<OrderResponse> findAllOrders() ;

    /**
     * Поиск заказа по его id.
     * @param orderId идентификатор заказа
     * @return ответ заказа
     */
    OrderResponse findOrderById(Long orderId) ;

    /**
     * Создание заказа.
     * @param requestCreatingOrder данные для запроса на создание заказа
     * @return ответ создания заказа
     */
    CreateOrderResponse addOrder(CreateOrderRequest requestCreatingOrder) ;

    /**
     * Обновление id курьера по id заказа.
     * @param courierId идентификатор курьера
     * @param orderId идентификатор заказа
     */
    void updateCourierId(Long courierId,Long orderId);

    /**
     * Обновление статуса заказа по его id.
     * @param status статус заказа
     * @param orderId идентификатор заказа
     */
    void updateOrderStatus(StatusOrder status,Long orderId);
}
