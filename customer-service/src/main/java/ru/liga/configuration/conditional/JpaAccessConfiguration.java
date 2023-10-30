package ru.liga.configuration.conditional;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.liga.mapping.CustomerMapper;
import ru.liga.repository.CustomerRepository;
import ru.liga.service.jpa.JpaCustomerService;

/**
 * Конфирурация для быстрого выбора jpa реализации работы с базой данных.
 */
@RequiredArgsConstructor
@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class JpaAccessConfiguration {

    private final CustomerRepository jpaCustomerRepository;
    private final CustomerMapper customerMapperMapStruct;

    /**
     * Создание бина реализации jpa для сервиса order.
     * @return
     */
    @Primary
    @Bean("jpaCustomerService")
    public JpaCustomerService orderService() {
        return new JpaCustomerService(jpaCustomerRepository, customerMapperMapStruct);
    }

}
