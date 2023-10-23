package ru.liga.service;

import ru.liga.dto.request.CreateOrderItemRequest;
import ru.liga.dto.response.OrderItemResponse;

import java.util.List;

public interface OrderItemService {
    List<OrderItemResponse> findAllOrderItems() ;
    OrderItemResponse findOrderItemById(Long id) ;
    OrderItemResponse addOrderItem(CreateOrderItemRequest creatingOrderItemRequest);
}
