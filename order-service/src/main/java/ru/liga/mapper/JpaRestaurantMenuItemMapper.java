package ru.liga.mapper;

import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.entity.RestaurantMenuItem;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JpaRestaurantMenuItemMapper {
    public static RestaurantMenuItemResponse map(RestaurantMenuItem restaurantMenuItem) {
        Long id = restaurantMenuItem.getId();
        BigDecimal price = restaurantMenuItem.getPrice();
        String description = restaurantMenuItem.getDescription();
        String imageUrl = restaurantMenuItem.getImage();
        return new RestaurantMenuItemResponse(id, price, description, imageUrl);
    }
    public static List<RestaurantMenuItemResponse> mapList(List<RestaurantMenuItem> restaurantMenuItems){
        List<RestaurantMenuItemResponse> result = new ArrayList<>();
        for(RestaurantMenuItem item: restaurantMenuItems){
            result.add(map(item));
        }
        return result;
    }
}
