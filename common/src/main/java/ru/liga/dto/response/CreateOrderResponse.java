package ru.liga.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderResponse {
    private Long OrderId;
    private String secretPaymentUrl;
    private OffsetDateTime estimatedTimeOfArrival;
}
