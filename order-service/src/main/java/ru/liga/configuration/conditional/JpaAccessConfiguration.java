package ru.liga.configuration.conditional;

import org.springframework.context.annotation.Bean;
import ru.liga.service.OrderItemService;
import ru.liga.service.OrderService;
import ru.liga.service.jpa.JpaOrderItemService;
import ru.liga.service.jpa.JpaOrderService;


public class JpaAccessConfiguration {
    @Bean("jpaOrderService")
    public OrderService orderService() {
        return new JpaOrderService();
    }
    @Bean("jpaOrderItemService")
    public OrderItemService orderItemService() {
        return new JpaOrderItemService();
    }

}
