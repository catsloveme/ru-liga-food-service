package ru.liga.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CourierResponse;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.enums.StatusCourier;

import java.util.List;

@FeignClient(name = "courier-service", url = "http://localhost:8080")
public interface CourierFeign {
    @GetMapping("/deliveries")
    ResponseEntity<List<CourierResponse>> findCourierByStatus(@RequestParam StatusCourier status);

}
