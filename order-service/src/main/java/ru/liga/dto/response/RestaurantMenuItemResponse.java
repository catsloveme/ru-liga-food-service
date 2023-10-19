package ru.liga.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class RestaurantMenuItemResponse {
    private Long id;
    private BigDecimal price;
//    private Integer quantity;
    private String description;
    private String imageUrl;

}
