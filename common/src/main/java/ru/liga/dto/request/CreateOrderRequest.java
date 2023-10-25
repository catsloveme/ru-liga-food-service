package ru.liga.dto.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CreateOrderRequest implements Serializable {
    private Long customerId;
    private Long restaurantId;
    private List<MenuItem> menuItems;

}
