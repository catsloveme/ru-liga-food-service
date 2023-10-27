package ru.liga.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ru.liga.dto.request.CreateOrderItemRequest;
import ru.liga.dto.response.OrderItemResponse;
import ru.liga.api.OrderItemService;

import java.util.List;

@Mapper
public interface OrderItemMapperBatis extends OrderItemService {

    List<OrderItemResponse> findAllOrderItems() ;
    OrderItemResponse findOrderItemById(Long id) ;
    OrderItemResponse addOrderItem(@Param("createOrderItemRequest")CreateOrderItemRequest creatingOrderItemRequest);
}
