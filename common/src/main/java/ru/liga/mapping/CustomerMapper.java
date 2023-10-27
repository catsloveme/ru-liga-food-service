package ru.liga.mapping;

import org.mapstruct.Mapper;
import ru.liga.dto.response.CustomerResponse;
import ru.liga.entity.Customer;
import ru.liga.mapping.abstraction.AbstractMapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends AbstractMapper<Customer,CustomerResponse> {

}
