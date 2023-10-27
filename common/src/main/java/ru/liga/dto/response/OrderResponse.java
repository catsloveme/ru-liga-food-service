package ru.liga.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long id;
    private RestaurantResponse restaurant;
    private List<RestaurantMenuItemResponse> items;
    private OffsetDateTime timestamp;
}
