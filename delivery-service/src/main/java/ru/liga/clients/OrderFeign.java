package ru.liga.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.liga.dto.response.OrderResponse;
import ru.liga.enums.StatusOrder;

/**
 * Класс Feign клиента.
 */
@FeignClient(name = "order-service", url = "http://localhost:8083")
public interface OrderFeign {
    /**
     * Метод, обновляющий id курьера у конкретного заказа, найденного по id.
     * @param courierId идентификатор курьера
     * @param orderId идентификатор заказа
     * @return ResponseEntity
     */
    @PatchMapping("/orders/courier/{courierId}/order/{orderId}")
    ResponseEntity<Void> updateCourierId(@PathVariable Long courierId, @PathVariable Long orderId);

    /**
     * Метод, обновляющий статус заказа по id.
     * @param orderId идентификатор заказа
     * @param status статус заказа
     * @return ResponseEntity
     */
    @PatchMapping("/orders/{orderId}")
    ResponseEntity<Void> updateOrderStatus(@PathVariable Long orderId, @RequestParam StatusOrder status);

    /**
     * Поиск заказа по id.
     * @param id идентификатор заказа.
     * @return ответ заказа
     */
    @GetMapping("/orders/{id}")
    ResponseEntity<OrderResponse> findOrderById(@PathVariable Long id);
}
