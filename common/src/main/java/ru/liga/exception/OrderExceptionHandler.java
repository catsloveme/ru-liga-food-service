package ru.liga.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.liga.dto.error.ErrorResponse;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class OrderExceptionHandler {
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(InvalidRequestParametersException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequestException(InvalidRequestParametersException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.badRequest()
            .body(ErrorResponse
                .builder()
                .exceptionMessage(BAD_REQUEST.getReasonPhrase())
                .exceptionName(BAD_REQUEST.name())
                .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .description("Invalid request parameters")
                .build());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundExceptionException(NotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.badRequest()
            .body(ErrorResponse
                .builder()
                .exceptionMessage(NOT_FOUND.getReasonPhrase())
                .exceptionName(NOT_FOUND.name())
                .code(String.valueOf(NOT_FOUND.value()))
                .description("Resource you are looking for is not found")
                .build());
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.badRequest()
            .body(ErrorResponse
                .builder()
                .exceptionMessage(INTERNAL_SERVER_ERROR.getReasonPhrase())
                .exceptionName(INTERNAL_SERVER_ERROR.name())
                .code(String.valueOf(INTERNAL_SERVER_ERROR.value()))
                .description("Server error")
                .build());
    }
}
