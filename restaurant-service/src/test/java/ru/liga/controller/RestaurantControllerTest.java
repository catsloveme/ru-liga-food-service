package ru.liga.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.liga.api.RestaurantMenuItemService;
import ru.liga.api.RestaurantService;
import ru.liga.service.rabbitMQ.NotificationService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig(RestaurantController.class)
@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;
    @MockBean
    private NotificationService notificationService;
    @MockBean
    private RestaurantMenuItemService restaurantMenuItemService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void acceptOrder() throws Exception {
        //Arrange
        Long orderId = 1L;
        //Act
        mockMvc.perform(
                patch("/restaurant-service/order/" + orderId + "/accept"))
            //Asset
            .andExpect(status().isOk())
        ;
    }

    @Test
    void denyOrder() throws Exception {
        //Arrange
        Long orderId = 1L;
        //Act
        mockMvc.perform(
                patch("/restaurant-service/order/" + orderId + "/denied"))
            //Asset
            .andExpect(status().isOk())
        ;
    }

    @Test
    void finishOrder() throws Exception {
        //Arrange
        Long orderId = 1L;
        //Act
        mockMvc.perform(
                patch("/restaurant-service/order/" + orderId + "/finish"))
            //Asset
            .andExpect(status().isOk())
        ;
    }
}
