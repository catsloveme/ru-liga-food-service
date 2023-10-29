package ru.liga.mapping.abstraction;

public interface ToDtoMapper<E, D> {
    /**
     * Преобразование entity в dto
     *
     * @return dto
     */
    D toDto(E entity);
}
