package ru.liga.exception;

import lombok.AllArgsConstructor;

/**
 * Класс исключения, которое выбрасывается когда параметры запроса некорректны.
 */

@AllArgsConstructor
public class NotFoundFreeCourierException extends RuntimeException {
    /**
     * Исключение, когда параметры запроса некорректны.
     *
     * @param message String
     */
    public NotFoundFreeCourierException(String message) {
        super(message);
    }
}
