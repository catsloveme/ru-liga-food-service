package ru.liga.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CreateOrderResponse;

@FeignClient(name = "order-service", url = "http://localhost:8083")
public interface OrderFeign {
    @PostMapping("/orders")
    ResponseEntity<CreateOrderResponse> addOrder(@RequestBody CreateOrderRequest requestCreatingOrder);

}
