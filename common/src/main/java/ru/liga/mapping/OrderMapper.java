package ru.liga.mapping;

import org.mapstruct.Mapping;
import ru.liga.dto.response.OrderResponse;
import ru.liga.entity.Order;

public interface OrderMapper {
    @Mapping(source = "order.id", target = "id")
    @Mapping(source = "order.timestamp", target = "timestamp")
    @Mapping(source = "order.restaurant", target = "restaurant")
    @Mapping(source = "order.items", target = "items")
    OrderResponse orderToOrderResponse(Order order);
}
