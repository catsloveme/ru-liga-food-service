package ru.liga.dto.request;

import java.util.List;
import lombok.Data;

@Data
public class CreateOrderRequest {
    private Long customerId;
    private Long restaurantId;
    private List<MenuItem> menuItems;

}
