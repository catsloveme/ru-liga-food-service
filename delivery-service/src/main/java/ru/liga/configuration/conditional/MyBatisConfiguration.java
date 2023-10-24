package ru.liga.configuration.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.liga.api.CourierService;
import ru.liga.service.batis.BatisCourierService;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "my_batis")
public class MyBatisConfiguration {

    @Primary
    @Bean("myBatisOrderService")
    public CourierService orderService() {
        return new BatisCourierService();
    }

}