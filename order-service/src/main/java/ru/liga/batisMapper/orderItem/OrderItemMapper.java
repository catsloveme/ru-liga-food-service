package ru.liga.batisMapper.orderItem;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ru.liga.dto.request.CreateOrderItemRequest;
import ru.liga.dto.response.OrderItemResponse;
import ru.liga.service.OrderItemService;

import java.util.List;

@Mapper
public interface OrderItemMapper extends OrderItemService {

    List<OrderItemResponse> findAllOrderItems() ;
    OrderItemResponse findOrderItemById(Long id) ;
    OrderItemResponse addOrderItem(@Param("createOrderItemRequest")CreateOrderItemRequest creatingOrderItemRequest);
}
