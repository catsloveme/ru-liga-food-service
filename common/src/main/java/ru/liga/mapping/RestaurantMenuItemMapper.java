package ru.liga.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.mapping.abstraction.AbstractMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMenuItemMapper
    extends AbstractMapper<RestaurantMenuItem, RestaurantMenuItemResponse> {
    @Mapping(source = "image", target = "imageUrl")
    RestaurantMenuItemResponse toDto(RestaurantMenuItem restaurantMenuItem);

    @Mapping(source = "image", target = "imageUrl")
    List<RestaurantMenuItemResponse> toDto(
        List<RestaurantMenuItem> restaurantMenuItem);
}
