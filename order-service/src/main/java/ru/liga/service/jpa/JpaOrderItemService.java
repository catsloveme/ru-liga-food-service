package ru.liga.service.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.dto.request.CreateOrderItemRequest;
import ru.liga.dto.response.OrderItemResponse;
import ru.liga.entity.OrderItem;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.exception.DataNotFoundException;
import ru.liga.mapping.JpaOrderItemMapper;
import ru.liga.repository.*;
import ru.liga.api.OrderItemService;

import java.util.List;
@RequiredArgsConstructor
public class JpaOrderItemService implements OrderItemService {

    private final JpaOrderItemRepository jpaOrderItemRepository;

    private final JpaOrderRepository jpaOrderRepository;

    private final JpaRestaurantMenuItemRepository jpaRestaurantMenuItemRepository;
   // Pageable firstPageWithTenElements = PageRequest.of(0, 10);


    public List<OrderItemResponse> findAllOrderItems() {
        List<OrderItem> orderItems = jpaOrderItemRepository.findAll();
        return JpaOrderItemMapper.mapToOrderItemList(orderItems);
    }


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

//    public void deleteOrderItemById(Long id){
//        jpaOrderItemRepository.deleteById(id);
//    }
}

