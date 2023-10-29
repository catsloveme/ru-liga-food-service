package ru.liga.service.batis;

import lombok.RequiredArgsConstructor;
import ru.liga.enums.StatusOrder;
import ru.liga.mapper.OrderMapperBatis;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.dto.response.OrderResponse;
import ru.liga.api.OrderService;

import java.util.List;

@RequiredArgsConstructor
public class BatisOrderService implements OrderService {
    private final OrderMapperBatis orderMapper;

    public List<OrderResponse> findAllOrders() {
        return orderMapper.findAllOrders();
    }

    public OrderResponse findOrderById(Long orderId) {
        return orderMapper.findOrderById(orderId);
    }

    public CreateOrderResponse addOrder(CreateOrderRequest createOrderRequest) {
        return orderMapper.addOrder(createOrderRequest);
    }

    @Override
    public void updateCourierId(Long orderId, Long courierId) {

    }

    @Override
    public void updateOrderStatus(StatusOrder status, Long orderId) {

    }
}
