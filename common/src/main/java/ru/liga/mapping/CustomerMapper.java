package ru.liga.mapping;

import org.mapstruct.Mapper;
import ru.liga.dto.response.CustomerResponse;
import ru.liga.entity.Customer;
import ru.liga.mapping.abstraction.AbstractMapper;

/**
 * Интервейс для преобразования сущности Customer в dto CustomerResponse.
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper
    extends AbstractMapper<Customer, CustomerResponse> {

}
