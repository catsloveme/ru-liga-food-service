package ru.liga.exception;

import lombok.AllArgsConstructor;

/**
 * Класс исключения, которое выбрасывается когда не найдена информация в базе данных.
 */

@AllArgsConstructor
public class DataNotFoundException extends RuntimeException {
    /**
     * Исключение, когда не найдена информация в базе данных.
     * @param message String
     */
    public DataNotFoundException(String message) {
        super(message);
    }
}
