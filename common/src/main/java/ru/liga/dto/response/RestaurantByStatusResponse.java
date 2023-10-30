package ru.liga.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestaurantByStatusResponse {
    private Long orderId;
    private List<RestaurantMenuItemResponse> restaurantMenuItems;


}
