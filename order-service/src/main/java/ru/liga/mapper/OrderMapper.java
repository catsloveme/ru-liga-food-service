package ru.liga.mapper;

import org.apache.ibatis.annotations.*;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.dto.response.OrderResponse;
import ru.liga.api.OrderService;

import java.util.List;
@Mapper

public interface OrderMapper extends OrderService{

    List<OrderResponse> findAllOrders();
    @Select("SELECT order_id, timestamp FROM orders WHERE order_id=#{id};")
    OrderResponse findOrderById(@Param("id") Long id);

    CreateOrderResponse addOrder(@Param("requestCreateOrder") CreateOrderRequest requestCreateOrder,@Param("customerId") Long customerId);
}
