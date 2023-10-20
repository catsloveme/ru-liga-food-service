package ru.liga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.dto.request.RequestCreatingOrder;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.dto.response.OrderResponse;
import ru.liga.entity.*;
import ru.liga.mapper.JpaOrderMapper;
import ru.liga.repository.*;


import java.time.OffsetDateTime;
import java.util.List;

import static ru.liga.enums.StatusOrder.CUSTOMER_CREATED;

@Service
public class JpaOrderService {
    @Autowired
    private JpaOrderRepository jpaOrderRepository;
    @Autowired
    private JpaOrderItemRepository jpaOrderItemRepository;
    @Autowired
    private JpaCustomerRepository jpaCustomerRepository;
    @Autowired
    private JpaRestaurantRepository jpaRestaurantRepository;
    @Autowired
    private JpaRestaurantMenuItemRepository jpaRestaurantMenuItemRepository;

    Pageable firstPageWithTenElements = PageRequest.of(0, 10);
    private Order order;

    @Transactional(readOnly = true)
    public List<OrderResponse> findAllOrders() {
        List<Order> orders = jpaOrderRepository.findAll();//firstPageWithTenElements);
        List<OrderItem> orderItems = jpaOrderItemRepository.findAll();
        return JpaOrderMapper.mapList(orders, orderItems);
    }

    @Transactional(readOnly = true)
    public OrderResponse findOrderById(Long orderId) {
        Order order = jpaOrderRepository.findOrderById(orderId);//, firstPageWithTenElements);
        List<OrderItem> orderItems = jpaOrderItemRepository.findByOrderId(orderId);
        return JpaOrderMapper.map(order, orderItems);
    }
    @Transactional
    public CreateOrderResponse createNewOrder(RequestCreatingOrder requestCreatingOrder, Long customerId) {
        order = new Order();
        order.setCustomer(jpaCustomerRepository.findById(customerId).orElseThrow());
        order.setStatus(CUSTOMER_CREATED);
        Long restaurantId = requestCreatingOrder.getRestaurantId();
        order.setRestaurant(jpaRestaurantRepository.findRestaurantById(restaurantId));
        order.setTimestamp(OffsetDateTime.now());
        jpaOrderRepository.save(order);
        return JpaOrderMapper.mapCreateOrder(order);
    }
}

