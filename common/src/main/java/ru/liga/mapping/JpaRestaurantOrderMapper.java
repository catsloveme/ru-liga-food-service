package ru.liga.mapping;

import ru.liga.dto.response.RestaurantByStatusResponse;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JpaRestaurantOrderMapper {
    public static List<RestaurantByStatusResponse> map( HashMap<Order, List<OrderItem>> orderItemsMapOrder) {
        List<RestaurantByStatusResponse> response = new ArrayList<>();
        List<RestaurantMenuItemResponse> valueMap;
       for(Order key: orderItemsMapOrder.keySet()){
           valueMap= orderItemsMapRestaurantMenuItems(orderItemsMapOrder.get(key));
           response.add(new RestaurantByStatusResponse(key.getId(),valueMap));
       }
       return response;

    }
    public static List<RestaurantMenuItemResponse> orderItemsMapRestaurantMenuItems(List<OrderItem> orderItems){
        List<RestaurantMenuItemResponse> restaurantMenuItems = new ArrayList<>();
        Long id;
        String name;
        String description;
        String imageUrl;
        BigDecimal price;
        Integer quantity;

        for(OrderItem item: orderItems){
            id = item.getRestaurantMenuItem().getId();
            name = item.getRestaurantMenuItem().getName();
            description = item.getRestaurantMenuItem().getDescription();
            imageUrl = item.getRestaurantMenuItem().getImage();
            price=item.getRestaurantMenuItem().getPrice();
            quantity = item.getQuantity();
            restaurantMenuItems.add(
                    new RestaurantMenuItemResponse(id, name,description,imageUrl,price));
        }
        return restaurantMenuItems;
    }
}
