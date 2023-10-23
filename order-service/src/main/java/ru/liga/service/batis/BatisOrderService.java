package ru.liga.service.batis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.batisMapper.order.OrderMapper;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.dto.response.OrderResponse;
import ru.liga.service.OrderService;

import java.util.List;

public class BatisOrderService implements OrderService {
    @Autowired
    OrderMapper orderMapper;

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
