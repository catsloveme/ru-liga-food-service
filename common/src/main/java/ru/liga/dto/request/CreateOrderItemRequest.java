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
public class CreateOrderItemRequest {
    private Long orderId;
    private Long restaurantMenuItemId;
    private BigDecimal price;
    private Integer quantity;
}
