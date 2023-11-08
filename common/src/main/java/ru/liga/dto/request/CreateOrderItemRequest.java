package ru.liga.dto.request;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderItemRequest {
    private UUID orderId;
    private UUID restaurantMenuItemId;
    private BigDecimal price;
    private Integer quantity;
}
