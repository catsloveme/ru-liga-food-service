package ru.liga.mapper;

import ru.liga.dto.response.OrderResponse;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.dto.response.RestaurantResponse;
import ru.liga.entity.Order;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public final class JpaOrderMapper {
    public static OrderResponse map(Order order){
        Long id = order.getId();
        RestaurantResponse restaurant = JpaRestaurantMapper.map(order.getRestaurant());
        List<RestaurantMenuItemResponse> items = JpaRestaurantMenuItemMapper.mapList(order.getRestaurantMenuItem());
        OffsetDateTime timestamp = order.getTimestamp();
        return new OrderResponse(id,restaurant,items,timestamp);
    }
    public static List<OrderResponse> mapList(List<Order> orders){
        List<OrderResponse> result = new ArrayList<>();
        for(Order item: orders){
            result.add(map(item));
        }
        return result;
    }
}
