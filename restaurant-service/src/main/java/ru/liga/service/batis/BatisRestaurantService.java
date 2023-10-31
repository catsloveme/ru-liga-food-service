package ru.liga.service.batis;

import java.util.List;
import lombok.RequiredArgsConstructor;
import ru.liga.api.RestaurantService;
import ru.liga.dto.response.RestaurantResponse;
import ru.liga.enums.StatusRestaurant;
import ru.liga.mapper.restaurant.RestaurantMapper;

/**
 * Сервис для работы с маппером MyBatis.
 */
@RequiredArgsConstructor
public class BatisRestaurantService implements RestaurantService {

    private final RestaurantMapper restaurantMapper;

    /**
     * Поиск ресторана по его id.
     *
     * @param id идентификатор ресторана
     * @return ответ ресторана
     */
    public RestaurantResponse findRestaurantById(Long id) {
        return restaurantMapper.findRestaurantById(id);
    }

    /**
     * Изменение статуса ресторана.
     *
     * @param status       статус ресторана
     * @param restaurantId идентификкатор ресторана
     */
    public void changeStatusById(StatusRestaurant status, Long restaurantId) {
        restaurantMapper.changeStatusById(status, restaurantId);
    }

    /**
     * Поиск всех ресторанов.
     *
     * @return список ответов ресторанов
     */
    public List<RestaurantResponse> findAllRestaurants() {
        return restaurantMapper.findAllRestaurants();
    }
}
