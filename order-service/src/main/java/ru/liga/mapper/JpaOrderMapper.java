package ru.liga.mapper;

import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.dto.response.OrderResponse;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.dto.response.RestaurantResponse;
import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public final class JpaOrderMapper {
    public static OrderResponse map(Order order, List<OrderItem> orderItems) {
        Long orderId = order.getId();
        RestaurantResponse restaurant = JpaRestaurantMapper.map(order.getRestaurant());
        List<RestaurantMenuItemResponse> items = JpaOrderItemMapper.map(orderItems);
        OffsetDateTime timestamp = order.getTimestamp();
        return new OrderResponse(orderId, restaurant, items, timestamp);
    }

    public static List<OrderResponse> mapList(List<Order> orders,List<OrderItem> orderItems) {
        List<OrderResponse> result = new ArrayList<>();
        for (Order item : orders) {
            result.add(map(item,orderItems));
        }
        return result;
    }

    public static CreateOrderResponse mapCreateOrder(Order order) {
        Long id = order.getId();
        OffsetDateTime timestamp = order.getTimestamp();
        return new CreateOrderResponse(id,null,timestamp);
    }
}
