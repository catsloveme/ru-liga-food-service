package ru.liga.configuration.conditional;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.liga.api.OrderItemService;
import ru.liga.api.OrderService;
import ru.liga.mapping.CreateOrderMapper;
import ru.liga.mapping.OrderItemMapper;
import ru.liga.mapping.OrderMapper;
import ru.liga.repository.CustomerRepository;
import ru.liga.repository.OrderItemRepository;
import ru.liga.repository.OrderRepository;
import ru.liga.repository.RestaurantMenuItemRepository;
import ru.liga.repository.RestaurantRepository;
import ru.liga.service.jpa.JpaOrderItemService;
import ru.liga.service.jpa.JpaOrderService;

/**
 * Конфирурация для быстрого выбора jpa реализации работы с базой данных.
 */
@RequiredArgsConstructor
@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class JpaAccessConfiguration {

    private final OrderRepository jpaOrderRepository;
    private final OrderItemRepository jpaOrderItemRepository;
    private final CustomerRepository jpaCustomerRepository;
    private final RestaurantRepository jpaRestaurantRepository;
    private final RestaurantMenuItemRepository jpaRestaurantMenuItemRepository;
    private final OrderItemMapper mapper;
    private final CreateOrderMapper mapperCreateOrder;
    private final OrderMapper mapperOrder;

    /**
     * Создание бина реализации jpa для сервиса Order.
     */
    @Primary
    @Bean("jpaOrderService")
    public OrderService orderService() {
        return new JpaOrderService(
            jpaOrderRepository,
            jpaCustomerRepository,
            jpaRestaurantRepository,
            mapperCreateOrder,
            mapperOrder
        );
    }

    /**
     * Создание бина реализации jpa для сервиса OrderItem.
     */
    @Primary
    @Bean("jpaOrderItemService")
    public OrderItemService orderItemService() {
        return new JpaOrderItemService(
            jpaOrderItemRepository,
            jpaOrderRepository,
            jpaRestaurantMenuItemRepository,
            mapper
        );
    }

}
