package ru.liga.service.batis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.batisMapper.restaurant.RestaurantMapper;
import ru.liga.dto.response.RestaurantByStatusResponse;
import ru.liga.dto.response.RestaurantResponse;
import ru.liga.enums.StatusRestaurant;
import ru.liga.service.RestaurantService;

import java.util.List;

@Service
public class BatisRestaurantService implements RestaurantService {
    @Autowired
    RestaurantMapper restaurantMapper;

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
