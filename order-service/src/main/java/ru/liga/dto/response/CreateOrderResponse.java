package ru.liga.dto.response;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class CreateOrderResponse {
    private Long OrderId;
    private String secretPaymentUrl;
    private OffsetDateTime estimatedTimeOfArrival;
}
