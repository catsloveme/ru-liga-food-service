package ru.liga.dto.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantMenuItemResponse {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;



}
