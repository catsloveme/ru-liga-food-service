package ru.liga.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantByStatusResponse {
    private Long orderId;
    private List<RestaurantMenuItemResponse> restaurantMenuItems;


}
