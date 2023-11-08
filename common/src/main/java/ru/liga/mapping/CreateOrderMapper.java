package ru.liga.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.entity.Order;
import ru.liga.mapping.abstraction.AbstractMapper;

/**
 * Интервейс для преобразования сущности Order в dto CreateOrderResponse.
 */
@Mapper(componentModel = "spring")
public interface CreateOrderMapper
    extends AbstractMapper<Order, CreateOrderResponse> {

    @Mapping(source = "timestamp", target = "estimatedTimeOfArrival")
   // @Mapping(source = "customer.address", target = "address")
    CreateOrderResponse toDto(Order order);
}
