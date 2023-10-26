package ru.liga.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "order-service", url = "http://localhost:8083")
public interface OrderFeign {
    @PatchMapping("/orders/courier/{courierId}/order/{orderId}")
    ResponseEntity<Void> updateCourierId( @PathVariable Long courierId,@PathVariable Long orderId);

}
