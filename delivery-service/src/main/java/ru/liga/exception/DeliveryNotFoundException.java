package ru.liga.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Log4j2
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Order")
public class DeliveryNotFoundException extends RuntimeException{
    public DeliveryNotFoundException(Long id){
        log.info("Заказ с id = {} не найден", id);
    }
}
