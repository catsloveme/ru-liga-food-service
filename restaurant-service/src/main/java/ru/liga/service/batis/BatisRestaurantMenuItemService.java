package ru.liga.service.batis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.mapper.restaurantMenuItem.RestaurantMenuItemMapper;
import ru.liga.dto.request.RestaurantMenuItemRequest;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.api.RestaurantMenuItemService;

import java.math.BigDecimal;


@Service
public class BatisRestaurantMenuItemService implements RestaurantMenuItemService {
    @Autowired
    private RestaurantMenuItemMapper restaurantMenuItemMapper;

    @Transactional(readOnly = true)
    public RestaurantMenuItemResponse findRestaurantMenuItemById(Long id) {
        return restaurantMenuItemMapper.findRestaurantMenuItemById(id);
    }

    @Transactional
    public RestaurantMenuItemResponse addRestaurantMenuItem(RestaurantMenuItemRequest request) {
        return restaurantMenuItemMapper.addRestaurantMenuItem(request);
}
    @Transactional
    public void deleteRestaurantMenuItemById(Long id){
        restaurantMenuItemMapper.deleteRestaurantMenuItemById(id);
    }
    @Transactional
    public void updatePrice(BigDecimal price, Long id){
        restaurantMenuItemMapper.updatePrice(price, id);
    }
}
