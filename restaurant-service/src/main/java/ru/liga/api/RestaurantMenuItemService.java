package ru.liga.api;

import java.math.BigDecimal;
import java.util.UUID;
import ru.liga.dto.request.RestaurantMenuItemRequest;
import ru.liga.dto.response.RestaurantMenuItemResponse;

/**
 * Интерфейс описания api сервиса RestaurantMenuItem.
 */
public interface RestaurantMenuItemService {

    /**
     * Добавить блюдо.
     *
     * @param request данные для запроса добавления блюда
     * @return ответ блюда
     */
    RestaurantMenuItemResponse addRestaurantMenuItem(RestaurantMenuItemRequest request);

    /**
     * Поиск блюда по его id.
     *
     * @param id идентификатор блюда
     * @return ответ блюда
     */
    RestaurantMenuItemResponse findRestaurantMenuItemById(UUID id);



    /**
     * Обновление цены блюда.
     *
     * @param price цена
     * @param id    идентификатор блюда
     */
    void updatePrice(BigDecimal price, UUID id);

}
