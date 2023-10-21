package ru.liga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.dto.request.CreateOrderItemRequest;
import ru.liga.dto.response.OrderItemResponse;
import ru.liga.entity.OrderItem;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.exception.DataNotFoundException;
import ru.liga.mapper.JpaOrderItemMapper;
import ru.liga.repository.*;

import java.util.List;

@Service
public class JpaOrderItemService {
    @Autowired
    private JpaOrderItemRepository jpaOrderItemRepository;
    @Autowired
    private JpaOrderRepository jpaOrderRepository;
    @Autowired
    private JpaRestaurantMenuItemRepository jpaRestaurantMenuItemRepository;
    Pageable firstPageWithTenElements = PageRequest.of(0, 10);

    @Transactional(readOnly = true)
    public List<OrderItemResponse> findAllOrderItems() {
        List<OrderItem> orderItems = jpaOrderItemRepository.findAll();
        return JpaOrderItemMapper.mapToOrderItemList(orderItems);
    }

    @Transactional(readOnly = true)
    public OrderItemResponse findOrderItemById(Long id) {
        OrderItem orderItem = jpaOrderItemRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException(String.format("Order Item id = %d not found", id)));
        return JpaOrderItemMapper.mapToOrderItem(orderItem);
    }

    @Transactional
    public OrderItemResponse addOrderItem(CreateOrderItemRequest creatingOrderItemRequest) {
        OrderItem orderItem = new OrderItem();
        Long orderId = creatingOrderItemRequest.getOrderId();
        orderItem.setOrder(jpaOrderRepository.findOrderById(orderId));

        Long menuItemId = creatingOrderItemRequest.getRestaurantMenuItemId();
        RestaurantMenuItem menuItem = jpaRestaurantMenuItemRepository.findById(menuItemId).orElseThrow(() ->
                new DataNotFoundException(String.format("Restaurant Menu Item id = %d not found", menuItemId)));
        orderItem.setRestaurantMenuItem(menuItem);

        orderItem.setPrice(creatingOrderItemRequest.getPrice());
        orderItem.setQuantity(creatingOrderItemRequest.getQuantity());

        jpaOrderItemRepository.save(orderItem);
        return JpaOrderItemMapper.mapToOrderItem(orderItem);
    }
//    @Transactional
//    public void deleteOrderItemById(Long id){
//        jpaOrderItemRepository.deleteById(id);
//    }
}

