package ru.liga.service.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.dto.response.RestaurantResponse;
import ru.liga.entity.Restaurant;
import ru.liga.enums.StatusRestaurant;
import ru.liga.exception.DataNotFoundException;
import ru.liga.mapping.abstraction.AbstractMapper;
import ru.liga.repository.JpaRestaurantRepository;
import ru.liga.api.RestaurantService;

import java.util.List;

@RequiredArgsConstructor
public class JpaRestaurantService implements RestaurantService {

    private final JpaRestaurantRepository jpaRestaurantRepository;
    private final AbstractMapper<Restaurant, RestaurantResponse> mapper;

    public RestaurantResponse findRestaurantById(Long id) {
        Restaurant restaurant = jpaRestaurantRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException(String.format("Restaurant menu item id = %d not found", id)));
        return mapper.toDto(restaurant);
    }

    @Transactional
    public void changeOrderStatusById(StatusRestaurant status, Long restaurantId) {
        jpaRestaurantRepository.updateRestaurantStatus(status, restaurantId);
    }


    public List<RestaurantResponse> findAllRestaurants() {
        List<Restaurant> restaurants = jpaRestaurantRepository.findAll();//firstPageWithTenElements);

        return mapper.toDto(restaurants);
    }
}
