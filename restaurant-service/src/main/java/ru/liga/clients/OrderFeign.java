package ru.liga.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.enums.StatusOrder;
import ru.liga.enums.StatusRestaurant;

@FeignClient(name = "order-service", url = "http://localhost:8083")
public interface OrderFeign {
    @PatchMapping("/restaurant/{id}")
    ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestParam StatusOrder status);

}
