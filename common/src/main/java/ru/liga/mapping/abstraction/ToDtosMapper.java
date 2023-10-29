package ru.liga.mapping.abstraction;

import java.util.List;

public interface ToDtosMapper<E, D> {
    /**
     * Преобразование списка entity в список dto
     *
     * @return dto
     */
    List<D> toDto(List<E> entity);
}
