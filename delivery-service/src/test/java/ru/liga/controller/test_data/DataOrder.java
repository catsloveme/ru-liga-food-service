package ru.liga.controller.test_data;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import ru.liga.dto.response.OrderResponse;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.dto.response.RestaurantResponse;
import static java.util.UUID.randomUUID;

public class DataOrder {
    public static OrderResponse getOrderResponse() {
        RestaurantResponse restaurant = RestaurantResponse
            .builder()
            .name("Вкусно и точка")
            .build();

        List<RestaurantMenuItemResponse> items = new ArrayList<>();
        OrderResponse order = OrderResponse
            .builder()
            .restaurant(restaurant)
            .id(randomUUID())
            .items(items)
            .timestamp(OffsetDateTime.now())
            .build();
        return order;
    }

}
