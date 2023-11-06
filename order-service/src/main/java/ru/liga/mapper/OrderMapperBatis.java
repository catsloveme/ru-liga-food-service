package ru.liga.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import ru.liga.api.OrderService;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.dto.response.OrderResponse;

/**
 * Интерфейс (меппер) для работы с myBatis.
 */
@Mapper
public interface OrderMapperBatis extends OrderService {

    /**
     * Поиск всех заказов.
     *
     * @return список ответов заказов
     */
    @Select("SELECT * FROM orders " +
        "LEFT JOIN restaurants ON orders.restaurant_id = restaurants.restaurant_id " +
        "LEFT JOIN order_items ON orders.customer_id = customers.customer_id ")
    List<OrderResponse> findAllOrders();

    /**
     * Поиск заказа по его id.
     *
     * @param id идентификатор заказа
     * @return ответ заказа
     */
    @Select("SELECT * FROM orders " +
        "LEFT JOIN restaurants ON orders.restaurant_id = restaurants.restaurant_id " +
        "LEFT JOIN order_items ON orders.customer_id = customers.customer_id " +
        "WHERE order_id = #{id}")
    OrderResponse findOrderById(@Param("id") Long id);

    /**
     * Создание заказа.
     *
     * @param requestCreateOrder данные для запроса на создание заказа
     * @return ответ создания заказа
     */

    CreateOrderResponse addOrder(
        @Param("requestCreateOrder") CreateOrderRequest requestCreateOrder
    );
}
