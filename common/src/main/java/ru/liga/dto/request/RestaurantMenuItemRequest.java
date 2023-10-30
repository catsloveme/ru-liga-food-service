package ru.liga.dto.request;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestaurantMenuItemRequest {
    private Long restaurantId;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
}
