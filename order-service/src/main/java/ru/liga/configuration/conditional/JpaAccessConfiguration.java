package ru.liga.configuration.conditional;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.liga.api.OrderItemService;
import ru.liga.api.OrderService;
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

    @Primary
    @Bean("jpaOrderService")
    public OrderService orderService() {
        return new JpaOrderService(jpaOrderRepository, jpaOrderItemRepository, jpaCustomerRepository, jpaRestaurantRepository);
    }


    @Primary
    @Bean("jpaOrderItemService")
    public OrderItemService orderItemService() {
        return new JpaOrderItemService(jpaOrderItemRepository, jpaOrderRepository, jpaRestaurantMenuItemRepository);
    }

}
