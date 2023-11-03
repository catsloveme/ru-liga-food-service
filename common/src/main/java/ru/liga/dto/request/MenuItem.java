package ru.liga.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenuItem {
    private Integer quantity;
    private Long restaurantMenuItemId;
}
