package ru.liga.mapping;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.liga.dto.response.CourierResponse;
import ru.liga.entity.Courier;
import ru.liga.mapping.abstraction.AbstractMapper;

/**
 * Интервейс для преобразования сущности Courier в dto CourierResponse.
 */
@Mapper(componentModel = "spring")
public interface CourierMapper
    extends AbstractMapper<Courier, CourierResponse> {
    /**
     * Преобразование сущности Courier в dto CourierResponse.
     * @param entity сущность
     * @return dto
     */
    @Mapping(source = "coordinates", target = "address")
    CourierResponse toDto(Courier entity);

    /**
     * Преобразование списка сущности Courier в список dto CourierResponse.
     * @param entity список сущностей
     * @return список dto
     */
    @Mapping(source = "coordinates", target = "address")
    List<CourierResponse> toDto(List<Courier> entity);
}

