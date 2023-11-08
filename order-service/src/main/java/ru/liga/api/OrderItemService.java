package ru.liga.api;

import java.util.List;
import java.util.UUID;
import ru.liga.dto.request.CreateOrderItemRequest;
import ru.liga.dto.response.OrderItemResponse;

/**
 * Интерфейс описания api сервиса OrderItem.
 */
public interface OrderItemService {
    /**
     * Поиск всех частей заказа.
     *
     * @return список ответов частей заказа
     */
    List<OrderItemResponse> findAllOrderItems();

    /**
     * Поиск части заказа по его id.
     *
     * @param id идентификатор части заказа
     * @return ответ части заказа
     */
    OrderItemResponse findOrderItemById(UUID id);

    /**
     * Добавление части заказа.
     *
     * @param creatingOrderItemRequest данные для запроса создания части заказа
     * @return ответ части заказа
     */
    OrderItemResponse addOrderItem(CreateOrderItemRequest creatingOrderItemRequest);
}
