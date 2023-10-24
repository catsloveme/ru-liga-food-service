package ru.liga.service.batis;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.mapper.OrderMapper;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.dto.response.OrderResponse;
import ru.liga.api.OrderService;

import java.util.List;
@RequiredArgsConstructor
public class BatisOrderService implements OrderService {
    private final OrderMapper orderMapper;
    @Transactional(readOnly = true)
    public List<OrderResponse> findAllOrders() {
        return orderMapper.findAllOrders();
    }

    @Transactional(readOnly = true)
    public OrderResponse findOrderById(Long orderId) {
        return orderMapper.findOrderById(orderId);
    }
    @Transactional
    public CreateOrderResponse addOrder(CreateOrderRequest createOrderRequest, Long customerId) {
        return orderMapper.addOrder(createOrderRequest, customerId);
}}
