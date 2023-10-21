package ru.liga.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderItemResponse {
    private Long id;
    private Long orderId;
    private Long restaurantMenuItemId;
    private BigDecimal price;
    private Integer quantity;
}
