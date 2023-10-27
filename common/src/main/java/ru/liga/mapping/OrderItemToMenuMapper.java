package ru.liga.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.entity.OrderItem;
import ru.liga.mapping.abstraction.AbstractMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemToMenuMapper extends AbstractMapper<OrderItem, RestaurantMenuItemResponse> {
    //так как в некоторых полях сущности OrderItem лежат другие сущности(order, restaurantMenuItem) , обращение идет через .
    @Mapping(source = "restaurantMenuItem.id", target = "id")
    @Mapping(source = "restaurantMenuItem.name", target = "name")
    @Mapping(source = "restaurantMenuItem.description", target = "description")
    @Mapping(source = "restaurantMenuItem.image", target = "imageUrl")
    @Mapping(source = "restaurantMenuItem.price", target = "price")
    RestaurantMenuItemResponse toDto(OrderItem orderItem);

    @Mapping(source = "restaurantMenuItem.id", target = "id")
    @Mapping(source = "restaurantMenuItem.name", target = "name")
    @Mapping(source = "restaurantMenuItem.description", target = "description")
    @Mapping(source = "restaurantMenuItem.image", target = "imageUrl")
    @Mapping(source = "restaurantMenuItem.price", target = "price")
    List<RestaurantMenuItemResponse> toDto(List<OrderItem> orderItem);

}
