package ru.liga.mapping.abstraction;

/**
 * Интерфейс для абстрактного создания маперов.
 * @param <E> первый тип, из которого необходимо преобразовать объект
 * @param <D> второй тип, в который необходимо предоразовать объект
 */
public interface AbstractMapper<E, D> extends ToDtoMapper<E, D>, ToDtosMapper<E, D> {
}
