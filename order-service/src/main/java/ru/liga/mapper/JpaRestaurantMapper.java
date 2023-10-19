package ru.liga.mapper;

import ru.liga.dto.response.RestaurantResponse;
import ru.liga.entity.Restaurant;

public class JpaRestaurantMapper {
    public static RestaurantResponse map(Restaurant restaurant) {
        Long id = restaurant.getId();
        String name = restaurant.getName();
        String address = restaurant.getAddress();
        return new RestaurantResponse(id, name, address);
    }
}
