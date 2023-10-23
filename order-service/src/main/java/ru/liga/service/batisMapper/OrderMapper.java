package ru.liga.service.batisMapper;

import org.apache.ibatis.annotations.*;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.dto.response.OrderResponse;
import ru.liga.service.OrderService;

import java.util.List;
@Mapper
public interface OrderMapper extends OrderService{

    List<OrderResponse> findAllOrders();

    OrderResponse findOrderById(Long orderId);

    CreateOrderResponse addOrder(@Param("requestCreateOrder") CreateOrderRequest requestCreateOrder,@Param("customerId") Long customerId);
}
