package ru.liga.service.jpa;

import lombok.RequiredArgsConstructor;
import ru.liga.dto.request.CreateOrderItemRequest;
import ru.liga.dto.response.OrderItemResponse;
import ru.liga.entity.OrderItem;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.exception.DataNotFoundException;
import ru.liga.mapping.abstraction.AbstractMapper;
import ru.liga.repository.*;
import ru.liga.api.OrderItemService;

import java.util.List;
@RequiredArgsConstructor
public class JpaOrderItemService implements OrderItemService {

    private final OrderItemRepository jpaOrderItemRepository;

    private final OrderRepository jpaOrderRepository;

    private final RestaurantMenuItemRepository jpaRestaurantMenuItemRepository;
   // Pageable firstPageWithTenElements = PageRequest.of(0, 10);
   private final AbstractMapper<OrderItem,OrderItemResponse> mapper;

    public List<OrderItemResponse> findAllOrderItems() {
        List<OrderItem> orderItems = jpaOrderItemRepository.findAll();
        return mapper.toDto(orderItems);
    }


    public OrderItemResponse findOrderItemById(Long id) {
        OrderItem orderItem = jpaOrderItemRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException(String.format("Order Item id = %d not found", id)));
        return mapper.toDto(orderItem);
    }


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
        return mapper.toDto(orderItem);
    }

//    public void deleteOrderItemById(Long id){
//        jpaOrderItemRepository.deleteById(id);
//    }
}

