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
import ru.liga.mapping.OrderItemToMenuMapper;
import ru.liga.repository.*;
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
    private final OrderItemToMenuMapper mapperOrderItem;
    private final RestaurantMapper mapperRestaurant;
    private final CreateOrderMapper mapperCreateOrder;

    /**
     * Создание бина реализации jpa для сервиса Order.
     * @return
     */
    @Primary
    @Bean("jpaOrderService")
    public OrderService orderService() {
        return new JpaOrderService(
            jpaOrderRepository,
            jpaOrderItemRepository,
            jpaCustomerRepository,
            jpaRestaurantRepository,
            mapperOrderItem,
            mapperRestaurant,
            mapperCreateOrder
        );
    }

    /**
     * Создание бина реализации jpa для сервиса OrderItem.
     * @return
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
