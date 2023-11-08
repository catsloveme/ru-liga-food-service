package ru.liga.mapping;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.liga.dto.response.CourierResponse;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.dto.response.CustomerResponse;
import ru.liga.dto.response.OrderItemResponse;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.dto.response.RestaurantResponse;
import ru.liga.entity.Courier;
import ru.liga.entity.Customer;
import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;
import ru.liga.entity.Restaurant;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.mapping.data.TestEntity;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {CourierMapperImpl.class,
    CreateOrderMapperImpl.class,
    CustomerMapperImpl.class,
    OrderItemMapperImpl.class,
    OrderItemToMenuMapperImpl.class,
    OrderItemMapperImpl.class,
    RestaurantMapperImpl.class,
    RestaurantMenuItemMapperImpl.class})
class MappersTest {
    private final TestEntity testEntity = new TestEntity();
    @Autowired
    private CourierMapper courierMapper;
    @Autowired
    private CreateOrderMapper createOrderMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderItemToMenuMapper orderItemToMenuMapper;
    @Autowired
    private RestaurantMapper restaurantMapper;
    @Autowired
    private RestaurantMenuItemMapper restaurantMenuItemMapper;

    @Test
    void courierToDto() {
        //Arrange
        Courier courier = testEntity.getCourier();
        CourierResponse expectedResponse = CourierResponse
            .builder()
            .id(courier.getId())
            .address(courier.getCoordinates())
            .build();

        //Act
        CourierResponse response = courierMapper.toDto(courier);

        //Asset
        assertThat(response.getAddress()).isEqualTo(expectedResponse.getAddress());
        assertThat(response.getId()).isEqualTo(expectedResponse.getId());
    }

    @Test
    void createOrderToDto() {
        //Arrange
        Order order = testEntity.getOrder();
        CreateOrderResponse expectedResponse = CreateOrderResponse
            .builder()
            .id(order.getId())
            .estimatedTimeOfArrival(order.getTimestamp())
            .build();

        //Act
        CreateOrderResponse response = createOrderMapper.toDto(order);

        //Asset
        assertThat(response.getEstimatedTimeOfArrival()).isEqualTo(expectedResponse.getEstimatedTimeOfArrival());
        assertThat(response.getId()).isEqualTo(expectedResponse.getId());
    }

    @Test
    void customerToDto() {
        //Arrange
        Customer customer = testEntity.getCustomer();
        CustomerResponse expectedResponse = CustomerResponse
            .builder()
            .id(customer.getId())
            .address(customer.getAddress())
            .email(customer.getEmail())
            .phone(customer.getPhone())
            .build();

        //Act
        CustomerResponse response = customerMapper.toDto(customer);

        //Asset
        assertThat(response.getAddress()).isEqualTo(expectedResponse.getAddress());
        assertThat(response.getId()).isEqualTo(expectedResponse.getId());
        assertThat(response.getEmail()).isEqualTo(expectedResponse.getEmail());
        assertThat(response.getPhone()).isEqualTo(expectedResponse.getPhone());
    }

    @Test
    void orderItemToDto() {
        //Arrange
        OrderItem orderItem = testEntity.getOrderItem();
        OrderItemResponse expectedResponse = OrderItemResponse
            .builder()
            .id(orderItem.getId())
            .orderId(orderItem.getOrder().getId())
            .price(orderItem.getPrice())
            .quantity(orderItem.getQuantity())
            .restaurantMenuItemId(orderItem.getRestaurantMenuItem().getId())
            .build();

        //Act
        OrderItemResponse response = orderItemMapper.toDto(orderItem);

        //Asset
        assertThat(response.getId()).isEqualTo(expectedResponse.getId());
        assertThat(response.getOrderId()).isEqualTo(expectedResponse.getOrderId());
        assertThat(response.getPrice()).isEqualTo(expectedResponse.getPrice());
        assertThat(response.getQuantity()).isEqualTo(expectedResponse.getQuantity());
        assertThat(response.getRestaurantMenuItemId()).isEqualTo(expectedResponse.getRestaurantMenuItemId());
    }

    @Test
    void orderItemToMenuToDto() {
        //Arrange
        OrderItem orderItem = testEntity.getOrderItem();
        RestaurantMenuItemResponse expectedResponse = RestaurantMenuItemResponse
            .builder()
            .id(orderItem.getRestaurantMenuItem().getId())
            .description(orderItem.getRestaurantMenuItem().getDescription())
            .imageUrl(orderItem.getRestaurantMenuItem().getImage())
            .price(orderItem.getRestaurantMenuItem().getPrice())
            .name(orderItem.getRestaurantMenuItem().getName())
            .build();

        //Act
        RestaurantMenuItemResponse response = orderItemToMenuMapper.toDto(orderItem);

        //Asset
        assertThat(response.getId()).isEqualTo(expectedResponse.getId());
        assertThat(response.getDescription()).isEqualTo(expectedResponse.getDescription());
        assertThat(response.getImageUrl()).isEqualTo(expectedResponse.getImageUrl());
        assertThat(response.getPrice()).isEqualTo(expectedResponse.getPrice());
        assertThat(response.getName()).isEqualTo(expectedResponse.getName());
    }

    @Test
    void restaurantToDto() {
        //Arrange
        Restaurant restaurant = testEntity.getRestaurant();
        RestaurantResponse expectedResponse = RestaurantResponse
            .builder()
            .id(restaurant.getId())
            .name(restaurant.getName())
            .address(restaurant.getAddress())
            .build();

        //Act
        RestaurantResponse response = restaurantMapper.toDto(restaurant);

        //Asset
        assertThat(response.getAddress()).isEqualTo(expectedResponse.getAddress());
        assertThat(response.getId()).isEqualTo(expectedResponse.getId());
        assertThat(response.getName()).isEqualTo(expectedResponse.getName());
    }

    @Test
    void restaurantMenuItemToDto() {
        //Arrange
        RestaurantMenuItem restaurantMenuItem = testEntity.getRestaurantMenuItem();
        RestaurantMenuItemResponse expectedResponse = RestaurantMenuItemResponse
            .builder()
            .id(restaurantMenuItem.getId())
            .name(restaurantMenuItem.getName())
            .price(restaurantMenuItem.getPrice())
            .imageUrl(restaurantMenuItem.getImage())
            .description(restaurantMenuItem.getDescription())
            .build();

        //Act
        RestaurantMenuItemResponse response = restaurantMenuItemMapper.toDto(restaurantMenuItem);

        //Asset

        assertThat(response.getId()).isEqualTo(expectedResponse.getId());
        assertThat(response.getDescription()).isEqualTo(expectedResponse.getDescription());
        assertThat(response.getImageUrl()).isEqualTo(expectedResponse.getImageUrl());
        assertThat(response.getPrice()).isEqualTo(expectedResponse.getPrice());
        assertThat(response.getName()).isEqualTo(expectedResponse.getName());
    }

}
