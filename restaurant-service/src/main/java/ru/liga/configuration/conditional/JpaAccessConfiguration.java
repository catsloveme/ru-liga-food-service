package ru.liga.configuration.conditional;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.liga.api.RestaurantMenuItemService;
import ru.liga.api.RestaurantService;
import ru.liga.mapping.RestaurantMapper;
import ru.liga.mapping.RestaurantMenuItemMapper;
import ru.liga.mapping.RestaurantMenuItemRequestMapper;
import ru.liga.repository.RestaurantMenuItemRepository;
import ru.liga.repository.RestaurantRepository;
import ru.liga.service.jpa.JpaRestaurantMenuItemService;
import ru.liga.service.jpa.JpaRestaurantService;

/**
 * Конфирурация для быстрого выбора jpa реализации работы с базой данных.
 */
@RequiredArgsConstructor
@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class JpaAccessConfiguration {
    private final RestaurantRepository jpaRestaurantRepository;
    private final RestaurantMenuItemRepository jpaRestaurantMenuItemRepository;
    private final RestaurantMapper mapperRestaurant;
    private final RestaurantMenuItemMapper mapperRestaurantMenuItem;
    private final RestaurantMenuItemRequestMapper restaurantMenuItemRequestMapper;

    /**
     * Создание бина реализации jpa для сервиса Restaurant.
     */
    @Primary
    @Bean("jpaRestaurantService")
    public RestaurantService restaurantService() {
        return new JpaRestaurantService(jpaRestaurantRepository, mapperRestaurant);
    }

    /**
     * Создание бина реализации jpa для сервиса RestaurantMenuItem.
     */
    @Primary
    @Bean("jpaRestaurantMenuItemService")
    public RestaurantMenuItemService restaurantMenuItemService() {
        return new JpaRestaurantMenuItemService(jpaRestaurantMenuItemRepository,
            jpaRestaurantRepository, mapperRestaurantMenuItem, restaurantMenuItemRequestMapper
        );
    }

}
