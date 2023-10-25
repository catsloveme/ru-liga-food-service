package ru.liga.configuration.conditional;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.liga.api.RestaurantMenuItemService;
import ru.liga.api.RestaurantService;
import ru.liga.repository.JpaOrderItemRepository;
import ru.liga.repository.JpaOrderRepository;
import ru.liga.repository.JpaRestaurantMenuItemRepository;
import ru.liga.repository.JpaRestaurantRepository;
import ru.liga.service.jpa.JpaRestaurantMenuItemService;
import ru.liga.service.jpa.JpaRestaurantService;

@RequiredArgsConstructor
@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class JpaAccessConfiguration {
    private final JpaRestaurantRepository jpaRestaurantRepository;

    private final JpaOrderRepository jpaOrderRepository;

    private final JpaOrderItemRepository jpaOrderItemRepository;
    private final JpaRestaurantMenuItemRepository jpaRestaurantMenuItemRepository;

    @Primary
    @Bean("jpaRestaurantService")
    public RestaurantService RestaurantService() {
        return new JpaRestaurantService(jpaRestaurantRepository,jpaOrderRepository,jpaOrderItemRepository);
    }
    @Primary
    @Bean("jpaRestaurantMenuItemService")
    public RestaurantMenuItemService restaurantMenuItemService() {
        return new JpaRestaurantMenuItemService(jpaRestaurantMenuItemRepository,jpaRestaurantRepository);
    }

}
