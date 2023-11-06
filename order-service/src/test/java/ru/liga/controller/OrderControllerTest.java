package ru.liga.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.liga.api.OrderItemService;
import ru.liga.api.OrderService;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.mapping.CreateOrderMapper;
import ru.liga.mapping.OrderItemToMenuMapper;
import ru.liga.mapping.RestaurantMapper;
import ru.liga.repository.CourierRepository;
import ru.liga.repository.CustomerRepository;
import ru.liga.repository.OrderItemRepository;
import ru.liga.repository.OrderRepository;
import ru.liga.repository.RestaurantMenuItemRepository;
import ru.liga.repository.RestaurantRepository;
import ru.liga.test_data.DataOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {
    private static CreateOrderRequest request;
    private static CreateOrderResponse expectedResponse;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private OrderService orderService;
    @MockBean
    private OrderItemService orderItemService;
    @MockBean
    private CustomerRepository jpaCustomerRepository;
    @MockBean
    private RestaurantRepository jpaRestaurantRepository;
    @MockBean
    private OrderItemToMenuMapper mapperOrderItem;
    @MockBean
    private RestaurantMapper mapperRestaurant;
    @MockBean
    private CreateOrderMapper mapperCreateOrder;
    @MockBean
    private OrderRepository jpaOrderRepository;
    @MockBean
    private OrderItemRepository jpaOrderItemRepository;
    @MockBean
    private RestaurantMenuItemRepository restaurantMenuItemRepository;
    @MockBean
    private CourierRepository courierRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        request = DataOrder.getCreateRequest();
        expectedResponse = CreateOrderResponse
            .builder()
            .id(1L)
            .estimatedTimeOfArrival(OffsetDateTime.now().plusHours(1L))
            .build();
    }

    @Test
    @WithMockUser
    void testAddOrder_Ok() throws Exception {
        when(orderService.addOrder(request)).thenReturn(expectedResponse);

        mockMvc.perform(
                post("/order-service")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(expectedResponse.getId()), Long.class))

        ;
    }

    @Test
    @WithMockUser
    void testAddOrder_BadRequest() throws Exception {
        when(orderService.addOrder(any(CreateOrderRequest.class))).thenReturn(expectedResponse);

        mockMvc.perform(
                post("/order-service")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

}
