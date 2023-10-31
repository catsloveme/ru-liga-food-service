package ru.liga.configuration.conditional;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.liga.api.CourierService;
import ru.liga.mapping.CourierMapper;
import ru.liga.repository.CourierRepository;
import ru.liga.service.jpa.JpaCourierService;

/**
 * Конфирурация для быстрого выбора jpa реализации работы с базой данных.
 */
@RequiredArgsConstructor
@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class JpaAccessConfiguration {
    private final CourierRepository jpaCourierRepository;
    private final CourierMapper mapper;

    /**
     * Создание бина реализации jpa для сервиса Courier.
     * @return
     */
    @Bean("jpaCourierService")
    public CourierService courierService() {
        return new JpaCourierService(jpaCourierRepository, mapper);
    }

}
