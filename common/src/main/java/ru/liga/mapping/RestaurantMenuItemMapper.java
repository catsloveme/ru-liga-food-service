package ru.liga.mapping;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.mapping.abstraction.AbstractMapper;

/**
 * Интервейс для преобразования сущности Restaurant в dto RestaurantResponse.
 */
@Mapper(componentModel = "spring")
public interface RestaurantMenuItemMapper
    extends AbstractMapper<RestaurantMenuItem, RestaurantMenuItemResponse> {
    /**
     * Преобразование сущности Restaurant в dto RestaurantResponse.
     * @param restaurantMenuItem сущность
     * @return dto
     */
    @Mapping(source = "image", target = "imageUrl")
    RestaurantMenuItemResponse toDto(RestaurantMenuItem restaurantMenuItem);

    /**
     * Преобразование списка сущностей Restaurant в список dto RestaurantResponse.
     * @param restaurantMenuItem список сущностей
     * @return список dto
     */
    @Mapping(source = "image", target = "imageUrl")
    List<RestaurantMenuItemResponse> toDto(
        List<RestaurantMenuItem> restaurantMenuItem);
}
