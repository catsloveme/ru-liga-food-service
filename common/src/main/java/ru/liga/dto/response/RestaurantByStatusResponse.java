package ru.liga.dto.response;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantByStatusResponse {
    private UUID orderId;
    private List<RestaurantMenuItemResponse> restaurantMenuItems;


}
