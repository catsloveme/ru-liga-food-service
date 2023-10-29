package ru.liga.api;

import ru.liga.dto.request.RestaurantMenuItemRequest;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import java.math.BigDecimal;

public interface RestaurantMenuItemService {
    RestaurantMenuItemResponse addRestaurantMenuItem(RestaurantMenuItemRequest request);

    RestaurantMenuItemResponse findRestaurantMenuItemById(Long id);

    void deleteRestaurantMenuItemById(Long id);

    void updatePrice(BigDecimal price, Long id);

}
