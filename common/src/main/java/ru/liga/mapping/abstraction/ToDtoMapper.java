package ru.liga.mapping.abstraction;

/**
 * Интерфейс для преобразования entity в dto.
 * @param <E> тип сущности
 * @param <D> тип dto
 */
public interface ToDtoMapper<E, D> {

    /**
     * Преобразование entity в dto.
     * @param entity сущность
     * @return dto
     */
    D toDto(E entity);
}
