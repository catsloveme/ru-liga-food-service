package ru.liga.mapper.restaurant;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ru.liga.api.RestaurantService;
import ru.liga.dto.response.RestaurantResponse;
import ru.liga.enums.StatusRestaurant;

/**
 * Интерфейс (меппер) для работы с myBatis.
 */
@Mapper
public interface RestaurantMapper extends RestaurantService {

    /**
     * Поиск всех ресторанов.
     *
     * @return список ответов ресторанов
     */
    @Select("SELECT restaurant_id as id, name as name, address as address FROM restaurants")
    List<RestaurantResponse> findAllRestaurants();

    /**
     * Поиск ресторана по его id.
     *
     * @param id идентификатор ресторана
     * @return ответ ресторана
     */

    @Select("SELECT restaurant_id as id, name as name, address as address FROM restaurants WHERE restaurant_id =#{id}")
    RestaurantResponse findRestaurantById(@Param("id") Long id);

    /**
     * Изменение статуса ресторана по его id.
     *
     * @param status       статус ресторана
     * @param restaurantId идентификкатор ресторана
     */
    @Update("UPDATE restaurants SET status = #{status} WHERE restaurant_id =#{id}")
    void changeStatusById(@Param("status") StatusRestaurant status, @Param("id") Long restaurantId);
}
