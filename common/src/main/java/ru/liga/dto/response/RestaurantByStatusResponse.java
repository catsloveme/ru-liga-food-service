package ru.liga.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class RestaurantByStatusResponse {
    private Long orderId;
    private List<RestaurantMenuItemResponse> restaurantMenuItems;


}
