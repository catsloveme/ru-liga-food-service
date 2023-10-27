package ru.liga.mapping.abstraction;

public interface AbstractMapper <E,D> extends ToEntityMapper<E,D>, ToDtoMapper<E,D>,ToDtosMapper<E,D>{
}
