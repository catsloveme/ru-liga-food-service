package ru.liga.mapper.restaurantMenuItem;

import java.math.BigDecimal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ru.liga.api.RestaurantMenuItemService;
import ru.liga.dto.request.RestaurantMenuItemRequest;
import ru.liga.dto.response.RestaurantMenuItemResponse;

/**
 * Интерфейс (меппер) для работы с myBatis.
 */
@Mapper
public interface RestaurantMenuItemMapper extends RestaurantMenuItemService {

    /**
     * Обновление цены блюда.
     *
     * @param price цена
     * @param id    идентификатор блюда
     */
    void updatePrice(@Param("price") BigDecimal price, @Param("id") Long id);

    /**
     * Добавить блюдо.
     *
     * @param request данные для запроса добавления блюда
     * @return ответ блюда
     */
    RestaurantMenuItemResponse addRestaurantMenuItem(@Param("request") RestaurantMenuItemRequest request);

    /**
     * Удаление блюда по его id.
     *
     * @param id идентификатор блюда
     */
    void deleteRestaurantMenuItemById(Long id);

}
