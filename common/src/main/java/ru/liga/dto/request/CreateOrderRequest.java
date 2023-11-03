package ru.liga.dto.request;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrderRequest {
    private Long customerId;
    private Long restaurantId;
    private List<MenuItem> menuItems;

}
