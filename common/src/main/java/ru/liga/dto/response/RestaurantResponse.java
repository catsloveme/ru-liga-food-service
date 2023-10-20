package ru.liga.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestaurantResponse {
    private Long id;
    private String name;
    private String address;

}
