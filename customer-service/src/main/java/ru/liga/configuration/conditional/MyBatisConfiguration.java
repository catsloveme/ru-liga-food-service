package ru.liga.configuration.conditional;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.liga.api.CustomerService;
import ru.liga.mapper.CustomerMapper;
import ru.liga.service.batis.BatisCustomerService;

@RequiredArgsConstructor
@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "my_batis")
public class MyBatisConfiguration {
    private final CustomerMapper customerMapper;

    /**
     * Создание бина реализации MyBatis для сервиса order.
     * @return
     */
    @Primary
    @Bean("myBatisCustomerService")
    public CustomerService orderService() {
        return new BatisCustomerService(customerMapper);
    }

}
