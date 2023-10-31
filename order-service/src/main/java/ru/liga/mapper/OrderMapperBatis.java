package ru.liga.mapper;

import org.apache.ibatis.annotations.*;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.dto.response.OrderResponse;
import ru.liga.api.OrderService;

import java.util.List;

/**
 * Интерфейс (меппер) для работы с myBatis.
 */
@Mapper
public interface OrderMapperBatis extends OrderService {

    /**
     * Поиск всех заказов.
     * @return список ответов заказов
     */
    List<OrderResponse> findAllOrders();

    /**
     * Поиск заказа по его id.
     * @param id идентификатор заказа
     * @return ответ заказа
     */
    @Select("SELECT order_id, timestamp FROM orders WHERE order_id=#{id};")
    OrderResponse findOrderById(@Param("id") Long id);

    /**
     * Создание заказа.
     * @param requestCreateOrder данные для запроса на создание заказа
     * @return ответ создания заказа
     */
    CreateOrderResponse addOrder(
        @Param("requestCreateOrder") CreateOrderRequest requestCreateOrder);
}
