package ru.liga.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.liga.dto.response.ErrorResponse;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class OrderExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(OrderNotFoundException exception) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(
                        "No such Order",
                        String.valueOf(NOT_FOUND.value()),
                        NOT_FOUND.name(),
                        NOT_FOUND.getReasonPhrase()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception exception) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(
                        "Server error",
                        String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                        INTERNAL_SERVER_ERROR.name(),
                        INTERNAL_SERVER_ERROR.getReasonPhrase()
                ));
    }
}
