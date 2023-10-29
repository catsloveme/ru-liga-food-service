package ru.liga.exception;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
//@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Order")
public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String message) {
        super(message);
    }
}
