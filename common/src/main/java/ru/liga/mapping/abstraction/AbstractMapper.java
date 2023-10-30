package ru.liga.mapping.abstraction;

public interface AbstractMapper<E, D>
    extends ToDtoMapper<E, D>,
    ToDtosMapper<E, D> {
}
