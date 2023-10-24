package ru.liga.configuration.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.liga.api.RestaurantMenuItemService;
import ru.liga.api.RestaurantService;
import ru.liga.service.jpa.JpaRestaurantMenuItemService;
import ru.liga.service.jpa.JpaRestaurantService;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class JpaAccessConfiguration {
    @Bean("jpaRestaurantService")
    public RestaurantService RestaurantService() {
        return new JpaRestaurantService();
    }
    @Bean("jpaRestaurantMenuItemService")
    public RestaurantMenuItemService restaurantMenuItemService() {
        return new JpaRestaurantMenuItemService();
    }

}
