package ru.liga.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.enums.StatusOrder;

@FeignClient(name = "order-service", url = "http://localhost:8083")
public interface OrderFeign {
    @PatchMapping("/orders/{orderId}")
    ResponseEntity<Void> updateOrderStatus(@PathVariable Long orderId, @RequestParam StatusOrder status);

}
