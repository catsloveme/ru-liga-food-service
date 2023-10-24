package ru.liga.service.batis;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.mapper.restaurant.RestaurantMapper;
import ru.liga.dto.response.RestaurantByStatusResponse;
import ru.liga.dto.response.RestaurantResponse;
import ru.liga.enums.StatusRestaurant;
import ru.liga.api.RestaurantService;

import java.util.List;

@RequiredArgsConstructor
public class BatisRestaurantService implements RestaurantService {

    private final RestaurantMapper restaurantMapper;

    @Transactional
    public RestaurantResponse findRestaurantById(Long id) {
        return restaurantMapper.findRestaurantById(id);
    }

    @Transactional(readOnly = true)
    public List<RestaurantByStatusResponse> findRestaurantsByStatus(StatusRestaurant status) {
        return restaurantMapper.findRestaurantsByStatus(status);

    }

    @Transactional(readOnly = true)
    public List<RestaurantResponse> findAllRestaurants() {
        return restaurantMapper.findAllRestaurants();
    }
}
