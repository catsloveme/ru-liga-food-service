package ru.liga.mapping;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.liga.dto.response.OrderResponse;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.dto.response.RestaurantResponse;
import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;
import ru.liga.entity.Restaurant;
import ru.liga.mapping.data.TestEntity;
import ru.liga.repository.OrderItemRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomOrderMappersTest {

    private final TestEntity testEntity = new TestEntity();
    @InjectMocks
    private OrderMapper orderMapper;
    @Mock
    private RestaurantMapper restaurantMapper;
    @Mock
    private OrderItemRepository jpaOrderItemRepository;
    @Mock
    private OrderItemToMenuMapper mapperOrderItem;

    @Test
    void orderToDto() {
        //Arrange
        RestaurantResponse restaurantResponse = new RestaurantResponse("Бургер Кинг", "31.22244, 54.22244");
        OrderItem orderItem = testEntity.getOrderItem();
        RestaurantMenuItemResponse restaurantMenuItemResponse = new RestaurantMenuItemResponse();
        when(restaurantMapper.toDto(any(Restaurant.class))).thenReturn(restaurantResponse);
        when(jpaOrderItemRepository.findByOrderId(any(UUID.class))).thenReturn(List.of(orderItem));
        when(mapperOrderItem.toDto(List.of(orderItem))).thenReturn(List.of(restaurantMenuItemResponse));

        Order order = testEntity.getOrder();
        OrderResponse expectedResponse = OrderResponse
            .builder()
            .id(order.getId())
            .restaurant(restaurantResponse)
            .items(List.of(restaurantMenuItemResponse))
            .timestamp(order.getTimestamp())
            .build();

        //Act
        OrderResponse response = orderMapper.toDto(order);

        //Asset
        assertThat(response.getId()).isEqualTo(expectedResponse.getId());
        assertThat(response.getTimestamp()).isEqualTo(expectedResponse.getTimestamp());
        assertThat(response.getItems()).isEqualTo(expectedResponse.getItems());
        assertThat(response.getRestaurant()).isEqualTo(expectedResponse.getRestaurant());
    }

}
