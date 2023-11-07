package ru.liga.service;

import java.time.OffsetDateTime;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.entity.Customer;
import ru.liga.entity.Order;
import ru.liga.entity.Restaurant;
import ru.liga.mapping.CreateOrderMapper;
import ru.liga.repository.CustomerRepository;
import ru.liga.repository.OrderRepository;
import ru.liga.repository.RestaurantRepository;
import ru.liga.service.jpa.JpaOrderService;
import ru.liga.test_data.DataOrder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderTest {
    private static CreateOrderRequest request;
    private static CreateOrderResponse expectedResponse;
    private static Customer customer;
    private static Restaurant restaurant;
    private static Order order;

    @Mock
    private OrderRepository jpaOrderRepository;
    @Mock
    private CustomerRepository jpaCustomerRepository;
    @Mock
    private RestaurantRepository jpaRestaurantRepository;
    @Mock
    private CreateOrderMapper mapperCreateOrder;
    @InjectMocks
    private JpaOrderService orderService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer();
        restaurant = new Restaurant();
        order = new Order();
        request = DataOrder.getCreateRequest();
        expectedResponse = CreateOrderResponse
            .builder()
            .estimatedTimeOfArrival(OffsetDateTime.now().plusHours(1L))
            .build();

    }

    @Test
    public void contextLoads() {
        assertThat(orderService).isNotNull();
    }

    @Test
    public void methodAddOrder_CreateOrderRequest_Success() {
        //Arrange
        when(jpaCustomerRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(customer));
        when(jpaRestaurantRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(restaurant));
        when(jpaOrderRepository.saveAndFlush(any(Order.class))).thenReturn(order);
        when(mapperCreateOrder.toDto(any(Order.class))).thenReturn(expectedResponse);

        //Act
        CreateOrderResponse testResponse = orderService.addOrder(request);

        //Assert
        assertThat(testResponse.getEstimatedTimeOfArrival()).isEqualTo(expectedResponse.getEstimatedTimeOfArrival());

    }

}
