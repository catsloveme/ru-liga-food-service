package ru.liga.dto.response;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponse {
    private UUID id;
    private String name;
    private String address;

    public RestaurantResponse(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
