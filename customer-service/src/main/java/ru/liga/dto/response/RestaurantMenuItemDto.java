package ru.liga.dto.response;

import lombok.Data;

@Data
public class RestaurantMenuItemDto {
    private Long id;
    private Integer price;
    private int quantity;
    private String description;
    private String imageName;

}
