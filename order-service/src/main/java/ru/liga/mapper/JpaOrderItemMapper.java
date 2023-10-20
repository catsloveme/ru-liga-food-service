package ru.liga.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.entity.OrderItem;
import ru.liga.repository.JpaRestaurantMenuItemRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JpaOrderItemMapper {
    @Autowired
    private JpaRestaurantMenuItemRepository jpaRestaurantMenuItemsRepository;
    public static List<RestaurantMenuItemResponse> map(List<OrderItem> orderItems) {
        List<RestaurantMenuItemResponse> result = new ArrayList<>();
        Long id;
        String name;
        String description;
        String imageUrl;
        BigDecimal price;
        Integer quantity;

        for (OrderItem item: orderItems){
            id = item.getId();
            name = item.getRestaurantMenuItem().getName();
            description = item.getRestaurantMenuItem().getDescription();
            imageUrl = item.getRestaurantMenuItem().getImage();
            price = item.getPrice();
            quantity = item.getQuantity();
            result.add(new RestaurantMenuItemResponse(id, name, description, imageUrl,price,quantity));
        }

        return result;
    }
//    public static List<RestaurantMenuItemResponse> mapList(List<RestaurantMenuItem> restaurantMenuItems){
//        List<RestaurantMenuItemResponse> result = new ArrayList<>();
//        for(RestaurantMenuItem item: restaurantMenuItems){
//            result.add(map(item));
//        }
//        return result;
//    }
}
