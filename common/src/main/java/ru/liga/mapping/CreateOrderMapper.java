package ru.liga.mapping;

import org.mapstruct.Mapper;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.entity.Order;
import ru.liga.mapping.abstraction.AbstractMapper;

@Mapper(componentModel = "spring")
public interface CreateOrderMapper extends AbstractMapper<Order, CreateOrderResponse> {


}
