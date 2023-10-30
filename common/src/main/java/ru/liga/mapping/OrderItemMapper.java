package ru.liga.mapping;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.liga.dto.response.OrderItemResponse;
import ru.liga.entity.OrderItem;
import ru.liga.mapping.abstraction.AbstractMapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper
    extends AbstractMapper<OrderItem, OrderItemResponse> {
    //так как в некоторых полях сущности OrderItem лежат другие
    // сущности(order, restaurantMenuItem) , обращение идет через .
    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "restaurantMenuItem.id", target = "restaurantMenuItemId")
    OrderItemResponse toDto(OrderItem orderItem);

    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "restaurantMenuItem.id", target = "restaurantMenuItemId")
    List<OrderItemResponse> toDto(List<OrderItem> orderItem);
}
