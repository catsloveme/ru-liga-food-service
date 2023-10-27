package ru.liga.configuration.conditional;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.liga.api.OrderItemService;
import ru.liga.api.OrderService;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.dto.response.OrderItemResponse;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.dto.response.RestaurantResponse;
import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;
import ru.liga.entity.Restaurant;
import ru.liga.mapping.abstraction.AbstractMapper;
import ru.liga.repository.*;
import ru.liga.service.jpa.JpaOrderItemService;
import ru.liga.service.jpa.JpaOrderService;

@RequiredArgsConstructor
@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class JpaAccessConfiguration {

    private final JpaOrderRepository jpaOrderRepository;
    private final JpaOrderItemRepository jpaOrderItemRepository;
    private final JpaCustomerRepository jpaCustomerRepository;
    private final JpaRestaurantRepository jpaRestaurantRepository;
    private final JpaRestaurantMenuItemRepository jpaRestaurantMenuItemRepository;
    private final AbstractMapper<OrderItem, OrderItemResponse> mapper;
    private final AbstractMapper<OrderItem, RestaurantMenuItemResponse> mapperOrderItem;
    private final AbstractMapper<Restaurant, RestaurantResponse> mapperRestaurant;
    private final AbstractMapper<Order, CreateOrderResponse> mapperCreateOrder;

    @Primary
    @Bean("jpaOrderService")
    public OrderService orderService() {
        return new JpaOrderService(jpaOrderRepository, jpaOrderItemRepository, jpaCustomerRepository, jpaRestaurantRepository, mapperOrderItem, mapperRestaurant, mapperCreateOrder);
    }


    @Primary
    @Bean("jpaOrderItemService")
    public OrderItemService orderItemService() {
        return new JpaOrderItemService(jpaOrderItemRepository, jpaOrderRepository, jpaRestaurantMenuItemRepository, mapper);
    }

}
