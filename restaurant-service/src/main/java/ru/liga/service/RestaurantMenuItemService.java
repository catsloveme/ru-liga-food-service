package ru.liga.service;

import ru.liga.dto.request.RestaurantMenuItemRequest;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import java.math.BigDecimal;

public interface RestaurantMenuItemService {
    RestaurantMenuItemResponse addRestaurantMenuItem(RestaurantMenuItemRequest request) ;
    void deleteRestaurantMenuItemById(Long id);
    void updatePrice(BigDecimal price, Long id);

}
