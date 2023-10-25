package ru.liga.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.enums.StatusRestaurant;

@FeignClient(name = "restaurant-service", url = "http://localhost:8081")
public interface RestaurantFeign {
//    @PatchMapping("/restaurant/{id}")
//    ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestParam StatusRestaurant status);
    }



