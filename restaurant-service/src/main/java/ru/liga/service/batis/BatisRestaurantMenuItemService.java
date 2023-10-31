package ru.liga.service.batis;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import ru.liga.api.RestaurantMenuItemService;
import ru.liga.dto.request.RestaurantMenuItemRequest;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.mapper.restaurantMenuItem.RestaurantMenuItemMapper;

/**
 * Сервис для работы с маппером MyBatis.
 */
@RequiredArgsConstructor
public class BatisRestaurantMenuItemService implements RestaurantMenuItemService {

    private final RestaurantMenuItemMapper restaurantMenuItemMapper;

    /**
     * Поиск блюда по его id.
     *
     * @param id дентификатор блюда
     * @return ответ блюда
     */
    public RestaurantMenuItemResponse findRestaurantMenuItemById(Long id) {
        return restaurantMenuItemMapper.findRestaurantMenuItemById(id);
    }

    /**
     * Добавление блюда.
     *
     * @param request данные для запроса добавления блюда
     * @return ответ блюда
     */
    public RestaurantMenuItemResponse addRestaurantMenuItem(RestaurantMenuItemRequest request) {
        return restaurantMenuItemMapper.addRestaurantMenuItem(request);
    }

    /**
     * Удаление блюда по его id.
     *
     * @param id идентификатор блюда
     */
    public void deleteRestaurantMenuItemById(Long id) {
        restaurantMenuItemMapper.deleteRestaurantMenuItemById(id);
    }

    /**
     * Обновление цены блюда.
     *
     * @param price цена
     * @param id    идентификатор блюда
     */
    public void updatePrice(BigDecimal price, Long id) {
        restaurantMenuItemMapper.updatePrice(price, id);
    }
}
