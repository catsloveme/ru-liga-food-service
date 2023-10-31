package ru.liga.configuration.conditional;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.liga.api.OrderItemService;
import ru.liga.api.OrderService;
import ru.liga.mapper.OrderItemMapperBatis;
import ru.liga.mapper.OrderMapperBatis;
import ru.liga.service.batis.BatisOrderItemService;
import ru.liga.service.batis.BatisOrderService;

/**
 * Конфирурация для быстрого выбора MyBatis реализации работы с базой данных.
 */
@RequiredArgsConstructor
@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "my_batis")
public class MyBatisConfiguration {
    private final OrderMapperBatis orderMapper;
    private final OrderItemMapperBatis orderItemMapper;

    /**
     * Создание бина реализации MyBatis для сервиса Order.
     */
    @Primary
    @Bean("myBatisOrderService")
    public OrderService orderService() {
        return new BatisOrderService(orderMapper);
    }

    /**
     * Создание бина реализации MyBatis для сервиса OrderItem.
     */
    @Primary
    @Bean("myBatisOrderItemService")
    public OrderItemService orderItemService() {
        return new BatisOrderItemService(orderItemMapper);
    }
}
