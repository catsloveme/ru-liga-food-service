package ru.liga.service.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.*;
import ru.liga.entity.*;
import ru.liga.enums.StatusOrder;
import ru.liga.exception.DataNotFoundException;
import ru.liga.mapping.abstraction.AbstractMapper;
import ru.liga.repository.*;
import ru.liga.api.OrderService;


import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static ru.liga.enums.StatusOrder.CUSTOMER_CREATED;

@RequiredArgsConstructor
public class JpaOrderService implements OrderService {

    private final JpaOrderRepository jpaOrderRepository;

    private final JpaOrderItemRepository jpaOrderItemRepository;

    private final JpaCustomerRepository jpaCustomerRepository;

    private final JpaRestaurantRepository jpaRestaurantRepository;
    private final AbstractMapper<OrderItem, RestaurantMenuItemResponse> mapperOrderItem;
    private final AbstractMapper<Restaurant, RestaurantResponse> mapperRestaurant;
    private final AbstractMapper<Order, CreateOrderResponse> mapperCreateOrder;
//    Pageable firstPageWithTenElements = PageRequest.of(0, 10);

    public List<OrderResponse> findAllOrders() {
        List<Order> orders = jpaOrderRepository.findAll();//firstPageWithTenElements);
        List<OrderResponse> responses = new ArrayList<>();
       for(Order order: orders){
           responses.add(this.findOrderById(order.getId()));
       }
        return responses;
    }

    public OrderResponse findOrderById(Long orderId) {
        Order order = jpaOrderRepository.findOrderById(orderId);//, firstPageWithTenElements);
        OffsetDateTime time = order.getTimestamp();
        RestaurantResponse restaurantResponse = mapperRestaurant.toDto(order.getRestaurant());
        List<OrderItem> orderItems = jpaOrderItemRepository.findByOrderId(orderId);
        List<RestaurantMenuItemResponse> menuItems = mapperOrderItem.toDto(orderItems);

        return OrderResponse.builder()
                .id(orderId)
                .restaurant(restaurantResponse)
                .items(menuItems)
                .timestamp(time)
                .build();
    }

    public CreateOrderResponse addOrder(CreateOrderRequest createOrderRequest) {
        Order order = new Order();

        Long customerId = createOrderRequest.getCustomerId();

        Customer customer = jpaCustomerRepository.findById(customerId).orElseThrow(() ->
                new DataNotFoundException(String.format("Customer id = %d not found", customerId)));

        order.setCustomer(customer);
        order.setStatus(CUSTOMER_CREATED);

        Long restaurantId = createOrderRequest.getRestaurantId();

        Restaurant restaurant = jpaRestaurantRepository.findById(restaurantId).orElseThrow(() ->
                new DataNotFoundException(String.format("Restaurant id = %d not found", restaurantId)));

        order.setRestaurant(restaurant);
        order.setTimestamp(OffsetDateTime.now());
        jpaOrderRepository.saveAndFlush(order);
        return mapperCreateOrder.toDto(order);
    }

    @Transactional
    public void updateCourierId(Long courierId, Long orderId) {
        jpaOrderRepository.updateCourierId(courierId,orderId);
    }
    @Transactional
    public void updateOrderStatus(StatusOrder status, Long orderId) {
        jpaOrderRepository.updateOrderStatus(status,orderId);
    }

}

