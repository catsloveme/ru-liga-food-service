package ru.liga.mapper;

import ru.liga.dto.response.RestaurantResponse;
import ru.liga.entity.Restaurant;
import java.util.ArrayList;
import java.util.List;

public class JpaRestaurantMapper {
    public static RestaurantResponse map(Restaurant restaurant) {
        Long id = restaurant.getId();
        String name = restaurant.getName();
        String address = restaurant.getAddress();
        return new RestaurantResponse(id, name, address);
    }
    public static List<RestaurantResponse> mapList(List<Restaurant> restaurants) {
        List<RestaurantResponse> result = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            result.add(map(restaurant));
        }
        return result;
    }
}
