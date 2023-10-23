package ru.liga.configuration.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.liga.service.OrderItemService;
import ru.liga.service.OrderService;
import ru.liga.service.batis.BatisOrderItemService;
import ru.liga.service.batis.BatisOrderService;
@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "my_batis")
public class MyBatisConfiguration {
    @Bean("myBatisOrderService")
    public OrderService orderService() {
        return new BatisOrderService();
    }
    @Bean("myBatisOrderItemService")
    public OrderItemService orderItemService() {
        return new BatisOrderItemService();
    }
}
