package ru.liga.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import ru.liga.dto.response.OrderItemResponse;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.entity.OrderItem;
import ru.liga.repository.JpaRestaurantMenuItemRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JpaOrderItemMapper {
    @Autowired
    private JpaRestaurantMenuItemRepository jpaRestaurantMenuItemsRepository;

    public static RestaurantMenuItemResponse map(OrderItem orderItems) {

        Long id;
        String name;
        String description;
        String imageUrl;
        BigDecimal price;
        //Integer quantity;


        id = orderItems.getId();
        name = orderItems.getRestaurantMenuItem().getName();
        description = orderItems.getRestaurantMenuItem().getDescription();
        imageUrl = orderItems.getRestaurantMenuItem().getImage();
        price = orderItems.getPrice();
        //quantity = orderItems.getQuantity();


        return new RestaurantMenuItemResponse(id, name, description, imageUrl, price);
    }

    public static List<RestaurantMenuItemResponse> mapList(List<OrderItem> orderItems) {
        List<RestaurantMenuItemResponse> result = new ArrayList<>();
        for (OrderItem item : orderItems) {
            result.add(map(item));
        }
        return result;
    }

    public static OrderItemResponse mapToOrderItem(OrderItem orderItems) {

        Long id;
        Long orderId;
        Long restaurantMenuItemId;
        BigDecimal price;
        Integer quantity;

        id = orderItems.getId();
        orderId = orderItems.getOrder().getId();
        restaurantMenuItemId = orderItems.getRestaurantMenuItem().getId();
        price = orderItems.getPrice();
        quantity = orderItems.getQuantity();
        return new OrderItemResponse(id, orderId, restaurantMenuItemId, price, quantity);
    }

    public static List<OrderItemResponse> mapToOrderItemList(List<OrderItem> orderItems) {
        List<OrderItemResponse> result = new ArrayList<>();
        for (OrderItem item : orderItems) {
            result.add(mapToOrderItem(item));
        }
        return result;
    }
}
