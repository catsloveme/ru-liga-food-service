package ru.liga.mapping;

import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.entity.RestaurantMenuItem;

import java.math.BigDecimal;

public class JpaRestaurantMenuItemMapper {
    public static RestaurantMenuItemResponse map(RestaurantMenuItem restaurantMenuItem) {
        Long id = restaurantMenuItem.getId();
        String name = restaurantMenuItem.getName();
        String description = restaurantMenuItem.getDescription();
        String imageUrl = restaurantMenuItem.getImage();
        BigDecimal price = restaurantMenuItem.getPrice();
        return new RestaurantMenuItemResponse(id, name, description, imageUrl, price);
    }
}
