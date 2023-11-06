package ru.liga.exception;

import lombok.AllArgsConstructor;

/**
 * Класс исключения, которое выбрасывается когда не найдена информация в базе данных.
 */

@AllArgsConstructor
public class NotFoundException extends RuntimeException {
    /**
     * Исключение, когда не найдена информация в базе данных.
     *
     * @param message String
     */
    public NotFoundException(String message) {
        super(message);
    }
}
