package ru.liga.api;

import java.util.List;
import ru.liga.dto.response.RestaurantResponse;
import ru.liga.enums.StatusRestaurant;

/**
 * Интерфейс описания api сервиса Restaurant.
 */
public interface RestaurantService {
    /**
     * Поиск всех ресторанов.
     *
     * @return список ответов ресторанов
     */
    List<RestaurantResponse> findAllRestaurants();

    /**
     * Поиск ресторана по его id.
     *
     * @param id идентификатор ресторана
     * @return ответ ресторана
     */
    RestaurantResponse findRestaurantById(Long id);

    /**
     * Изменение статуса ресторана по его id.
     *
     * @param status       статус ресторана
     * @param restaurantId идентификкатор ресторана
     */
    void changeStatusById(StatusRestaurant status, Long restaurantId);
}
