package ru.liga.service.jpa;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.api.RestaurantService;
import ru.liga.dto.response.RestaurantResponse;
import ru.liga.entity.Restaurant;
import ru.liga.enums.StatusRestaurant;
import ru.liga.exception.DataNotFoundException;
import ru.liga.mapping.RestaurantMapper;
import ru.liga.repository.RestaurantRepository;

/**
 * Сервис для работы с репозиторием jpa.
 */
@RequiredArgsConstructor
public class JpaRestaurantService implements RestaurantService {

    private final RestaurantRepository jpaRestaurantRepository;
    private final RestaurantMapper mapper;

    /**
     * Поиск ресторана по его id.
     *
     * @param id идентификатор ресторана
     * @return ответ ресторана
     */
    public RestaurantResponse findRestaurantById(Long id) {
        Restaurant restaurant = jpaRestaurantRepository.findById(id).orElseThrow(() ->
            new DataNotFoundException(String.format("Restaurant menu item id = %d not found", id)));
        return mapper.toDto(restaurant);
    }

    /**
     * Изменение стратуса ресторана.
     *
     * @param status       статус ресторана
     * @param restaurantId идентификкатор ресторана
     */
    @Transactional
    public void changeStatusById(StatusRestaurant status, Long restaurantId) {
        jpaRestaurantRepository.updateRestaurantStatus(status, restaurantId);
    }

    /**
     * Поиск всех  ресторанов.
     *
     * @return список ответов ресторанов
     */
    public List<RestaurantResponse> findAllRestaurants() {
        List<Restaurant> restaurants = jpaRestaurantRepository.findAll();//firstPageWithTenElements);

        return mapper.toDto(restaurants);
    }
}
