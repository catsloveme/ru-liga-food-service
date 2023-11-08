package ru.liga.test_data;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.request.MenuItem;
import ru.liga.dto.response.OrderResponse;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.dto.response.RestaurantResponse;
import ru.liga.entity.Order;
import static java.util.UUID.randomUUID;

public class DataOrder {
    public static CreateOrderRequest getCreateRequest() {
        UUID id = randomUUID();
        UUID restaurantId = randomUUID();
        List<MenuItem> menuItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            menuItems.add(MenuItem
                .builder()
                .quantity(1)
                .restaurantMenuItemId(id)
                .build());
        }

        CreateOrderRequest request = CreateOrderRequest
            .builder()
            .customerId(id)
            .restaurantId(restaurantId)
            .menuItems(menuItems)
            .build();
        return request;
    }

    public static OrderResponse getOrderResponse() {
        UUID id = randomUUID();
        RestaurantResponse restaurant = RestaurantResponse
            .builder()
            .name("Вкусно и точка")
            .build();

        List<RestaurantMenuItemResponse> items = new ArrayList<>();
        OrderResponse order = OrderResponse
            .builder()
            .restaurant(restaurant)
            .id(id)
            .items(items)
            .timestamp(OffsetDateTime.now())
            .build();
        return order;
    }

    public static List<Order> getOrderListEntity() {
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        return orders;
    }
}
