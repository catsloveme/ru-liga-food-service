package ru.liga.service.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.dto.response.OrderResponse;
import ru.liga.entity.*;
import ru.liga.exception.DataNotFoundException;
import ru.liga.mapping.JpaOrderMapper;
import ru.liga.repository.*;
import ru.liga.api.OrderService;


import java.time.OffsetDateTime;
import java.util.List;

import static ru.liga.enums.StatusOrder.CUSTOMER_CREATED;

@RequiredArgsConstructor
public class JpaOrderService implements OrderService {

    private final JpaOrderRepository jpaOrderRepository;

    private final JpaOrderItemRepository jpaOrderItemRepository;

    private final JpaCustomerRepository jpaCustomerRepository;

    private final JpaRestaurantRepository jpaRestaurantRepository;


//    Pageable firstPageWithTenElements = PageRequest.of(0, 10);

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
    public CreateOrderResponse addOrder(CreateOrderRequest createOrderRequest) {
        Order order = new Order();
        Long customerId = createOrderRequest.getCustomerId();
        order.setCustomer(jpaCustomerRepository.findById(customerId).orElseThrow(() ->
                        new DataNotFoundException(String.format("Customer id = %d not found", customerId))));
        order.setStatus(CUSTOMER_CREATED);
        Long restaurantId = createOrderRequest.getRestaurantId();
        order.setRestaurant(jpaRestaurantRepository.findById(restaurantId).orElseThrow(() ->
                new DataNotFoundException(String.format("Restaurant id = %d not found", restaurantId))));
        order.setTimestamp(OffsetDateTime.now());
        jpaOrderRepository.save(order);
        return JpaOrderMapper.mapCreateOrder(order);
    }

//    @Transactional
//    public void deleteOrderById(Long id){
//        jpaOrderRepository.deleteById(id);
//    }
}

