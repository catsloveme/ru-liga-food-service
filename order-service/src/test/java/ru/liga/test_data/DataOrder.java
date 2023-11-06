package ru.liga.test_data;

import java.util.ArrayList;
import java.util.List;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.request.MenuItem;

public class DataOrder {
    public static CreateOrderRequest getCreateRequest() {
        Long customerId = 1L;
        Long restaurantId = 1L;
        List<MenuItem> menuItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            menuItems.add(MenuItem
                .builder()
                .quantity(1)
                .restaurantMenuItemId(1L)
                .build());
        }

        CreateOrderRequest request = CreateOrderRequest
            .builder()
            .customerId(customerId)
            .restaurantId(restaurantId)
            .menuItems(menuItems)
            .build();
        return request;
    }
}
