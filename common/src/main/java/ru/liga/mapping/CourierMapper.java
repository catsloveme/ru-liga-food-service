package ru.liga.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.liga.dto.response.CourierResponse;
import ru.liga.entity.Courier;
import ru.liga.mapping.abstraction.AbstractMapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CourierMapper extends AbstractMapper<Courier, CourierResponse> {
    @Mapping(source = "coordinates", target = "address")
    CourierResponse toDto(Courier entity);

    @Mapping(source = "coordinates", target = "address")
    List<CourierResponse> toDto(List<Courier> entity);
}

