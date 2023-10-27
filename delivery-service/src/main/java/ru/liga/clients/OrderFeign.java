package ru.liga.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.liga.enums.StatusOrder;


@FeignClient(name = "order-service", url = "http://localhost:8083")
public interface OrderFeign {
    @PatchMapping("/orders/courier/{courierId}/order/{orderId}")
    ResponseEntity<Void> updateCourierId( @PathVariable Long courierId,@PathVariable Long orderId);

    @PatchMapping("/orders/{orderId}")
    ResponseEntity<Void> updateOrderStatus(@PathVariable Long orderId, @RequestParam StatusOrder status);

}
