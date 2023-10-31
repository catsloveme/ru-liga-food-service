package ru.liga.configuration.conditional;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.liga.api.CustomerService;
import ru.liga.mapper.CustomerMapper;
import ru.liga.service.batis.BatisCustomerService;

/**
 * Конфирурация для быстрого выбора MyBatis реализации работы с базой данных.
 */
@RequiredArgsConstructor
@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "my_batis")
public class MyBatisConfiguration {
    private final CustomerMapper customerMapper;

    /**
     * Создание бина реализации MyBatis для сервиса Customer.
     * @return
     */
    @Primary
    @Bean("myBatisCustomerService")
    public CustomerService customerService() {
        return new BatisCustomerService(customerMapper);
    }

}
