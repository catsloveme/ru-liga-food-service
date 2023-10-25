package ru.liga.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long id;
   // private RestaurantResponse restaurant;
   // private List<RestaurantMenuItemResponse> items;
    private OffsetDateTime timestamp;
}
