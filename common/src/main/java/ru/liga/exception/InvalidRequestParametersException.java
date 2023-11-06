package ru.liga.exception;

import lombok.AllArgsConstructor;

/**
 * Класс исключения, которое выбрасывается когда параметры запроса некорректны.
 */

@AllArgsConstructor
public class InvalidRequestParametersException extends RuntimeException {
    /**
     * Исключение, когда параметры запроса некорректны.
     *
     * @param message String
     */
    public InvalidRequestParametersException(String message) {
        super(message);
    }
}
