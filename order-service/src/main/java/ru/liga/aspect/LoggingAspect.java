package ru.liga.aspect;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.request.MenuItem;
import ru.liga.exception.NotFoundException;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
    @Pointcut(value = "execution(public ru.liga.dto.response.CreateOrderResponse "
        + "ru.liga.service.jpa.JpaOrderService.addOrder(ru.liga.dto.request.CreateOrderRequest))")
    public void addOrder() {
    }

    @Before(value = "addOrder()")
    public void logBeforeExecution(JoinPoint joinPoint) {
        CreateOrderRequest args = (CreateOrderRequest) joinPoint.getArgs()[0];
        Long customerId = args.getCustomerId();
        Long restaurantId = args.getRestaurantId();
        List<MenuItem> menuItems = args.getMenuItems();
        log.info(
            "Заказчик с id = {} заказывает из ресторана id = {} следующие блюда {}",
            customerId,
            restaurantId,
            menuItems.toString()
        );

    }

    @AfterReturning(value = "addOrder()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSourceLocation().getWithinType().getName();

        log.debug("Отработал метод {} класса {} с результатом {}",
            methodName, className, result.toString());
        log.info("Успешное создание нового заказа : {}", result);
    }

    @AfterThrowing(value = "addOrder()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, NotFoundException exception) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSourceLocation().getWithinType().getName();
        log.debug("Метод {} класса {} был аварийно завершен с исключением",
            methodName, className, exception);
        log.warn("Произошел сбой в программе, попробуйте позже создать заказ");
    }

}
