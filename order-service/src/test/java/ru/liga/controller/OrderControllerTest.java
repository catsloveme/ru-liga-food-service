package ru.liga.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.OffsetDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.liga.api.OrderItemService;
import ru.liga.api.OrderService;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.dto.response.OrderResponse;
import ru.liga.test_data.DataOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig(OrderController.class)
@WebMvcTest(OrderController.class)
class OrderControllerTest {
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

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    @Test
    @WithMockUser
    void testAddOrder_Ok() throws Exception {
        //Arrange
        CreateOrderRequest request = DataOrder.getCreateRequest();
        expectedResponse = CreateOrderResponse
            .builder()
            .id(1L)
            .estimatedTimeOfArrival(OffsetDateTime.now().plusHours(1L))
            .build();

        when(orderService.addOrder(request)).thenReturn(expectedResponse);

        //Act
        mockMvc.perform(
                post("/order-service")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON))
            //Asset
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(expectedResponse.getId()), Long.class))

        ;
    }

    @Test
    @WithMockUser
    void testAddOrder_BadRequest() throws Exception {
        //Arrange
        when(orderService.addOrder(any(CreateOrderRequest.class))).thenReturn(expectedResponse);

        //Act
        mockMvc.perform(
                post("/order-service")
                    .contentType(MediaType.APPLICATION_JSON))
            //Asset
            .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    void testFindAllOrders_Ok() throws Exception {
        //Arrange
        OrderResponse orderResponse = DataOrder.getOrder();
        when(orderService.findAllOrders()).thenReturn(List.of(orderResponse));

        //Act
        mockMvc.perform(get("/order-service")
                .accept(MediaType.APPLICATION_JSON))
            //Asset
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id", is(orderResponse.getId()), Long.class))
            .andExpect(jsonPath("$[0].restaurant.name", is(orderResponse.getRestaurant().getName())))
            .andExpect(jsonPath("$[0].items", is(orderResponse.getItems())));
    }

    @Test
    @WithMockUser
    void testFindOrderById_Ok() throws Exception {
        //Arrange
        OrderResponse orderResponse = DataOrder.getOrder();
        when(orderService.findOrderById(orderResponse.getId())).thenReturn(orderResponse);

        //Act
        mockMvc.perform(get("/order-service/" + orderResponse.getId())
                .accept(MediaType.APPLICATION_JSON))
            //Asset
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(orderResponse.getId()), Long.class))
            .andExpect(jsonPath("$.restaurant.name", is(orderResponse.getRestaurant().getName())));
    }

}
