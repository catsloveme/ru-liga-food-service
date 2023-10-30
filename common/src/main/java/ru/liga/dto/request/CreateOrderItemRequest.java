package ru.liga.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CreateOrderItemRequest {
    private Long orderId;
    private Long restaurantMenuItemId;
    private BigDecimal price;
    private Integer quantity;
}
