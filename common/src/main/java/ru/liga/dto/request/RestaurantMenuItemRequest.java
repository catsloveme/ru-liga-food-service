package ru.liga.dto.request;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantMenuItemRequest {
    private Long restaurantId;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
}
