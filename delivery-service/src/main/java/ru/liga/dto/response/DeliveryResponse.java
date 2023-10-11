package ru.liga.dto.response;

import lombok.Data;

@Data
public class DeliveryResponse {
    private Long OrderId;
    private Restaurant restaurant;
    private Customer customer;
    private String payment;
}
