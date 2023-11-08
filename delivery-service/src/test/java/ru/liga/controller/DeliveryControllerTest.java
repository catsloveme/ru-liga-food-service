package ru.liga.controller;

import java.util.List;
import java.util.UUID;
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
import ru.liga.api.CourierService;
import ru.liga.controller.test_data.DataOrder;
import ru.liga.dto.response.OrderResponse;
import ru.liga.service.rabbitMQ.NotificationService;
import static java.util.UUID.randomUUID;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig(DeliveryController.class)
@WebMvcTest(DeliveryController.class)
class DeliveryControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourierService courierService;
    @MockBean
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @WithMockUser
    void findFinishOrder_success() throws Exception {

        //Arrange
        OrderResponse orderResponse = DataOrder.getOrderResponse();
        when(courierService.findFinishOrder()).thenReturn(List.of(orderResponse));

        //Act
        mockMvc.perform(get("/delivery-service/orders")
                .accept(MediaType.APPLICATION_JSON))
            //Asset
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].restaurant.name", is(orderResponse.getRestaurant().getName())))
            .andExpect(jsonPath("$[0].items", is(orderResponse.getItems())));

    }

    @Test
    void finishDelivery_success() throws Exception {
        //Arrange
        UUID orderId = randomUUID();
        UUID courierId = randomUUID();
        //Act
        mockMvc.perform(
                post("/delivery-service/finishOrder/" + orderId + "/courier/" + courierId))
            //Asset
            .andExpect(status().isOk())
        ;
    }
}
