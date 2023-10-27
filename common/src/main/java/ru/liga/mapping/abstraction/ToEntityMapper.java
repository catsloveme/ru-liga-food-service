package ru.liga.mapping.abstraction;

public interface ToEntityMapper<E,D> {
    /**
     *  Преобразование dto в entity
     * @param dto
     * @return entity
     */
    E toEntity(D dto);
}
