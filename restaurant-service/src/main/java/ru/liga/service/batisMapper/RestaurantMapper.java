package ru.liga.service.batisMapper;

import org.apache.ibatis.annotations.Mapper;
import ru.liga.dto.response.RestaurantByStatusResponse;
import ru.liga.dto.response.RestaurantResponse;
import ru.liga.enums.StatusRestaurant;
import ru.liga.service.RestaurantService;

import java.util.List;

@Mapper
public interface RestaurantMapper extends RestaurantService {

    List<RestaurantByStatusResponse> findRestaurantsByStatus(StatusRestaurant status) ;
    List<RestaurantResponse> findAllRestaurants();
}
