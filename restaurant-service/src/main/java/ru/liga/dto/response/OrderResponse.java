package ru.liga.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {
    private Long orderId;
    private List<RestaurantMenuItem> restaurantMenuItems;

}
