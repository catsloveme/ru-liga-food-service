package ru.liga.configuration.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.liga.api.RestaurantMenuItemService;
import ru.liga.api.RestaurantService;
import ru.liga.service.batis.BatisRestaurantMenuItemService;
import ru.liga.service.batis.BatisRestaurantService;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "my_batis")
public class MyBatisConfiguration {

    @Primary
    @Bean("myBatisRestaurantService")
    public RestaurantService restaurantService() {
        return new BatisRestaurantService();
    }
    @Primary
    @Bean("myBatisRestaurantMenuItemService")
    public RestaurantMenuItemService restaurantMenuItemService() {
        return new BatisRestaurantMenuItemService();
    }
}
