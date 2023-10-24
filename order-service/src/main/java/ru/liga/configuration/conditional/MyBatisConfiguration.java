package ru.liga.configuration.conditional;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.liga.api.OrderItemService;
import ru.liga.api.OrderService;
import ru.liga.mapper.OrderItemMapper;
import ru.liga.mapper.OrderMapper;
import ru.liga.service.batis.BatisOrderItemService;
import ru.liga.service.batis.BatisOrderService;
@RequiredArgsConstructor
@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "my_batis")
public class MyBatisConfiguration {
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    @Primary
    @Bean("myBatisOrderService")
    public OrderService orderService() {
        return new BatisOrderService(orderMapper);
    }
    @Primary
    @Bean("myBatisOrderItemService")
    public OrderItemService orderItemService() {
        return new BatisOrderItemService(orderItemMapper);
    }
}
