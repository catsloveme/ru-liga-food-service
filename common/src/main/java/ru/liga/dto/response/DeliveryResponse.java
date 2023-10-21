package ru.liga.dto.response;

import lombok.Data;
import ru.liga.entity.Restaurant;

@Data
public class DeliveryResponse {
    private Long orderId;
    private Restaurant restaurant;
    private CourierResponse customer;
    private String payment;
}
