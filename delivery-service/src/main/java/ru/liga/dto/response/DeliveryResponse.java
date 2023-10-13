package ru.liga.dto.response;

import lombok.Data;

@Data
public class DeliveryResponse {
    private Long orderId;
    private Restaurant restaurant;
    private Customer customer;
    private String payment;
}
