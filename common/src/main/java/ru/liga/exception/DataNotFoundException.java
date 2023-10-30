package ru.liga.exception;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Класс исключения, которое выбрасывается когда не найдена информация в базе данных.
 */
@Log4j2
@AllArgsConstructor
//@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Order")
public class DataNotFoundException extends RuntimeException {
    /**
     * Исключение, когда не найдена информация в базе данных.
     * @param message String
     */
    public DataNotFoundException(String message) {
        super(message);
    }
}
