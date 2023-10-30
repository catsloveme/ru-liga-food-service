package ru.liga.mapping;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.entity.OrderItem;
import ru.liga.mapping.abstraction.AbstractMapper;

/**
 * Интервейс для преобразования сущности OrderItem в dto RestaurantMenuItemResponse.
 */
@Mapper(componentModel = "spring")
public interface OrderItemToMenuMapper
    extends AbstractMapper<OrderItem, RestaurantMenuItemResponse> {
    /**
     * Преобразование сущности OrderItem в dto RestaurantMenuItemResponse.
     * так как в некоторых полях сущности OrderItem лежат другие
     * сущности(order, restaurantMenuItem) , обращение идет через .
     * @param orderItem сущность
     * @return dto
     */
    @Mapping(source = "restaurantMenuItem.id", target = "id")
    @Mapping(source = "restaurantMenuItem.name", target = "name")
    @Mapping(source = "restaurantMenuItem.description", target = "description")
    @Mapping(source = "restaurantMenuItem.image", target = "imageUrl")
    @Mapping(source = "restaurantMenuItem.price", target = "price")
    RestaurantMenuItemResponse toDto(OrderItem orderItem);

    /**
     * Преобразование списка сущностей OrderItem в список dto RestaurantMenuItemResponse.
     * @param orderItem сущность
     * @return
     */
    @Mapping(source = "restaurantMenuItem.id", target = "id")
    @Mapping(source = "restaurantMenuItem.name", target = "name")
    @Mapping(source = "restaurantMenuItem.description", target = "description")
    @Mapping(source = "restaurantMenuItem.image", target = "imageUrl")
    @Mapping(source = "restaurantMenuItem.price", target = "price")
    List<RestaurantMenuItemResponse> toDto(List<OrderItem> orderItem);

}
