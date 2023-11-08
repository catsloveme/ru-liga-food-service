package ru.liga.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.liga.enums.StatusOrder;

/**
 * Класс Feign клиента.
 */
@FeignClient(name = "order-service", url = "http://localhost:8000")
public interface OrderFeign {
    /**
     * Метод, обновляющий статус у конкретного заказа, найденного по id.
     *
     * @param orderId идентификатор заказа
     * @param status  статус заказа
     * @return ResponseEntity
     */
    @PatchMapping("/order-service/{orderId}")
    ResponseEntity<Void> updateOrderStatus(@PathVariable Long orderId, @RequestParam StatusOrder status);

}
