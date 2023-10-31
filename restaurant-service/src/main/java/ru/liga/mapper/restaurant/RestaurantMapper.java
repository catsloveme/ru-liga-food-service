package ru.liga.mapper.restaurant;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import ru.liga.api.RestaurantService;
import ru.liga.dto.response.RestaurantByStatusResponse;
import ru.liga.dto.response.RestaurantResponse;
import ru.liga.enums.StatusRestaurant;

/**
 * Интерфейс (меппер) для работы с myBatis.
 */
@Mapper
public interface RestaurantMapper extends RestaurantService {

    /**
     * Поиск ресторана по статусу.
     *
     * @param status статус ресторана
     * @return список ответов ресторанов
     */
    List<RestaurantByStatusResponse> findRestaurantsByStatus(StatusRestaurant status);

    /**
     * Поиск всех ресторанов.
     *
     * @return список ответов ресторанов
     */
    List<RestaurantResponse> findAllRestaurants();
}
