package ru.liga.mapping.abstraction;

public interface ToEntityMapper<E, D> {
    /**
     * Преобразование dto в entity.
     * @param dto класс, который нужно преобразовать в сущность
     * @return entity
     */
    E toEntity(D dto);
}
