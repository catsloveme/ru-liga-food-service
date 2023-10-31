package ru.liga.mapping.abstraction;

/**
 * Интерфейс для преобразования dto в entity.
 * @param <E> тип сущности
 * @param <D> тип dto
 */
public interface ToEntityMapper<E, D> {

    /**
     * Преобразование dto в entity.
     * @param dto
     * @return entity
     */
    E toEntity(D dto);
}
