package ru.liga.configuration.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.liga.service.OrderItemService;
import ru.liga.service.OrderService;
import ru.liga.service.batis.BatisOrderItemService;
import ru.liga.service.batis.BatisOrderService;
@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "my_batis")
public class MyBatisConfiguration {

    @Primary
    @Bean("myBatisOrderService")
    public OrderService orderService() {
        return new BatisOrderService();
    }
    @Primary
    @Bean("myBatisOrderItemService")
    public OrderItemService orderItemService() {
        return new BatisOrderItemService();
    }
}
