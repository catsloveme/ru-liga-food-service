package ru.liga.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class CreateOrderRequest {
    private Long customerId;
    private Long restaurantId;
    private List<MenuItem> menuItems;

}
