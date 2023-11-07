package ru.liga.mapper.restaurantMenuItem;

import java.math.BigDecimal;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ru.liga.api.RestaurantMenuItemService;
import ru.liga.dto.request.RestaurantMenuItemRequest;
import ru.liga.dto.response.RestaurantMenuItemResponse;

/**
 * Интерфейс (меппер) для работы с myBatis.
 */
@Mapper
public interface RestaurantMenuItemMapper extends RestaurantMenuItemService {

    /**
     * Добавить блюдо.
     *
     * @param request данные для запроса добавления блюда
     * @return ответ блюда
     */
    @Results(value = {
        @Result(property = "id", column = "restaurant_menu_item_id"),
        @Result(property = "name", column = "name"),
        @Result(property = "price", column = "price"),
        @Result(property = "imageUrl", column = "image"),
        @Result(property = "description", column = "description")
    })
    @Select("INSERT INTO restaurant_menu_items (restaurant_id, name, price, image, description)" +
        " VALUES (#{restaurantId}, #{name}, #{price}, #{imageUrl},#{description})")
    RestaurantMenuItemResponse addRestaurantMenuItem(RestaurantMenuItemRequest request);

    /**
     * Поиск блюда по его id.
     *
     * @param id идентификатор блюда
     * @return ответ блюда
     */
    @Select("SELECT restaurant_menu_item_id as id, * FROM restaurant_menu_items WHERE restaurant_menu_item_id =#{id}")
    RestaurantMenuItemResponse findRestaurantMenuItemById(@Param("id") Long id);

    /**
     * Удаление блюда по id.
     *
     * @param id идентификатор блюда
     */
    @Delete("DELETE FROM restaurant_menu_items WHERE restaurant_menu_item_id =#{id}")
    void deleteRestaurantMenuItemById(@Param("id") Long id);

    /**
     * Обновление цены блюда.
     *
     * @param price цена
     * @param id    идентификатор блюда
     */
    @Update("UPDATE restaurant_menu_items SET price = #{price} WHERE restaurant_menu_item_id =#{id}")
    void updatePrice(@Param("price") BigDecimal price, @Param("id") Long id);

}
