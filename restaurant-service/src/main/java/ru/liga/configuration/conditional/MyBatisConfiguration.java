package ru.liga.configuration.conditional;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.liga.api.RestaurantMenuItemService;
import ru.liga.api.RestaurantService;
import ru.liga.mapper.restaurant.RestaurantMapper;
import ru.liga.mapper.restaurantMenuItem.RestaurantMenuItemMapper;
import ru.liga.service.batis.BatisRestaurantMenuItemService;
import ru.liga.service.batis.BatisRestaurantService;

/**
 * Конфирурация для быстрого выбора MyBatis реализации работы с базой данных.
 */
@RequiredArgsConstructor
@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "my_batis")
public class MyBatisConfiguration {
    private final RestaurantMapper restaurantMapper;
    private final RestaurantMenuItemMapper restaurantMenuItemMapper;

    /**
     * Создание бина реализации MyBatis для сервиса Restaurant.
     */
    @Primary
    @Bean("myBatisRestaurantService")
    public RestaurantService restaurantService() {

        return new BatisRestaurantService(restaurantMapper);
    }

    /**
     * Создание бина реализации MyBatis для сервиса RestaurantMenuItem.
     */
    @Primary
    @Bean("myBatisRestaurantMenuItemService")
    public RestaurantMenuItemService restaurantMenuItemService() {
        return new BatisRestaurantMenuItemService(restaurantMenuItemMapper);
    }
}
