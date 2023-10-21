package ru.liga.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class RestaurantMenuItemRequest {
    private Long restaurantId;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
}
