package ru.liga.dto.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderResponse {
    private Long id;
    private String secretPaymentUrl;
    private OffsetDateTime estimatedTimeOfArrival;
}
