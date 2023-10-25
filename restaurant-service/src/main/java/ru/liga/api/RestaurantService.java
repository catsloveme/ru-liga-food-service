package ru.liga.api;

import ru.liga.dto.response.RestaurantByStatusResponse;
import ru.liga.dto.response.RestaurantResponse;
import ru.liga.enums.StatusCourier;
import ru.liga.enums.StatusRestaurant;
import java.util.List;

public interface RestaurantService {
    List<RestaurantByStatusResponse> findRestaurantsByStatus(StatusRestaurant status) ;
    List<RestaurantResponse> findAllRestaurants();
    RestaurantResponse findRestaurantById(Long id);
    void changeOrderStatusById( StatusRestaurant status,Long restaurantId );
}
