package ru.liga.api;

import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.dto.response.OrderResponse;
import ru.liga.enums.StatusOrder;

import java.util.List;

public interface OrderService {
    List<OrderResponse> findAllOrders() ;
    OrderResponse findOrderById(Long orderId) ;
    CreateOrderResponse addOrder(CreateOrderRequest requestCreatingOrder) ;
    void updateCourierId(Long courierId,Long orderId);
    void updateOrderStatus(StatusOrder status,Long orderId);
}
