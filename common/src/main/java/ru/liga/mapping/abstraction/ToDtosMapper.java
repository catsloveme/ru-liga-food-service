package ru.liga.mapping.abstraction;

import java.util.List;

public interface ToDtosMapper<E, D> {
    /**
     * Преобразование списка entity в список dto.
     * @param entity сущность
     * @return dto
     */
    List<D> toDto(List<E> entity);
}
