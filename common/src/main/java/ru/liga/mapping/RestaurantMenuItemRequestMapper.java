package ru.liga.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.liga.dto.request.RestaurantMenuItemRequest;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.mapping.abstraction.AbstractMapper;


/**
 * Интервейс для преобразования сущности RestaurantMenuItem в dto RestaurantMenuItemRequest и наоборот.
 */
@Mapper(componentModel = "spring")
public interface RestaurantMenuItemRequestMapper
    extends AbstractMapper<RestaurantMenuItem, RestaurantMenuItemRequest> {
    /**
     * Преобразование dto в сущность.
     *
     * @return сущность
     */
    @Mapping(source = "imageUrl", target = "image")
    @Mapping(source = "restaurantId", target = "restaurant.id")
    RestaurantMenuItem toEntity(RestaurantMenuItemRequest request);
}
