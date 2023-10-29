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

    public RestaurantResponse findRestaurantById(Long id) {
        return restaurantMapper.findRestaurantById(id);
    }

    public void changeOrderStatusById(StatusRestaurant status, Long restaurantId) {
        restaurantMapper.changeOrderStatusById(status, restaurantId);
    }

    public List<RestaurantByStatusResponse> findRestaurantsByStatus(StatusRestaurant status) {
        return restaurantMapper.findRestaurantsByStatus(status);

    }

    public List<RestaurantResponse> findAllRestaurants() {
        return restaurantMapper.findAllRestaurants();
    }
}
