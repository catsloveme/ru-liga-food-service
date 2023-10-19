package ru.liga.mapper;

import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.entity.RestaurantMenuItem;

import java.math.BigDecimal;

public final class JpaMapper {
    public static RestaurantMenuItemResponse map(RestaurantMenuItem restaurantMenuItem){
        Long id = restaurantMenuItem.getId();
        BigDecimal price = restaurantMenuItem.getPrice();
        String description = restaurantMenuItem.getDescription();
        String imageUrl = restaurantMenuItem.getName();
        return new RestaurantMenuItemResponse(id,price,description,imageUrl);
    }
}
