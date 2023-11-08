package ru.liga.configuration.conditional;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.liga.api.CourierService;
import ru.liga.mapping.CourierMapper;
import ru.liga.mapping.OrderMapper;
import ru.liga.repository.CourierRepository;
import ru.liga.repository.OrderRepository;
import ru.liga.service.jpa.JpaCourierService;

/**
 * Конфирурация для быстрого выбора jpa реализации работы с базой данных.
 */
@RequiredArgsConstructor
@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class JpaAccessConfiguration {
    private final CourierRepository jpaCourierRepository;
    private final OrderRepository orderRepository;
    private final CourierMapper courierMapper;
    private final OrderMapper orderMapper;

    /**
     * Создание бина реализации jpa для сервиса Courier.
     */
    @Primary
    @Bean("jpaCourierService")
    public CourierService courierService() {
        return new JpaCourierService(jpaCourierRepository, orderRepository, courierMapper, orderMapper);
    }

}
