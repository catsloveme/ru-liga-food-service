package ru.liga.mapping;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.liga.dto.response.OrderResponse;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.dto.response.RestaurantResponse;
import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;
import ru.liga.entity.Restaurant;
import ru.liga.repository.OrderItemRepository;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final RestaurantMapper restaurantMapper;
    private final OrderItemRepository jpaOrderItemRepository;
    private final OrderItemToMenuMapper mapperOrderItem;

    public OrderResponse toDto(Order order) {
        UUID orderId = order.getId();
        OffsetDateTime time = order.getTimestamp();
        Restaurant restaurant = order.getRestaurant();
        RestaurantResponse restaurantResponse = restaurantMapper.toDto(restaurant);
        List<OrderItem> orderItems = jpaOrderItemRepository.findByOrderId(orderId);
        List<RestaurantMenuItemResponse> menuItems = mapperOrderItem.toDto(orderItems);

        OrderResponse response = OrderResponse
            .builder()
            .id(orderId)
            .restaurant(restaurantResponse)
            .timestamp(time)
            .items(menuItems)
            .build();
        return response;
    }

    public List<OrderResponse> toDtos(List<Order> order) {
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order element : order) {
            orderResponses.add(toDto(element));
        }
        return orderResponses;
    }
}
