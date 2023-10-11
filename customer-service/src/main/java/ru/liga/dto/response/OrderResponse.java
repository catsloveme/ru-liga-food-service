package ru.liga.dto.response;


import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;


@Data
public class OrderResponse {

    private Long id;
    private RestaurantDto restaurant;
    private List<RestaurantMenuItemDto> items;
    private OffsetDateTime timestamp;
}
