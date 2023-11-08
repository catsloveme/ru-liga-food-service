package ru.liga.mapping;

import org.mapstruct.Mapper;
import ru.liga.dto.response.RestaurantResponse;
import ru.liga.entity.Restaurant;
import ru.liga.mapping.abstraction.AbstractMapper;

/**
 * Интервейс для преобразования сущности Restaurant в dto RestaurantResponse.
 */
@Mapper(componentModel = "spring")
public interface RestaurantMapper
    extends AbstractMapper<Restaurant, RestaurantResponse> {

}
