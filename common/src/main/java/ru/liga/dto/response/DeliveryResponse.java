package ru.liga.dto.response;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.liga.entity.Restaurant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryResponse {
    private UUID orderId;
    private Restaurant restaurant;
    private CourierResponse customer;
    private String payment;
}
