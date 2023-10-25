package ru.liga.configuration.conditional;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.liga.api.CourierService;
import ru.liga.mapper.DeliveryMapper;
import ru.liga.service.batis.BatisCourierService;

@RequiredArgsConstructor
@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "my_batis")
public class MyBatisConfiguration {
    private final DeliveryMapper deliveryMapper;

    @Bean("myBatisOrderService")
    public CourierService orderService() {
        return new BatisCourierService(deliveryMapper);
    }

}