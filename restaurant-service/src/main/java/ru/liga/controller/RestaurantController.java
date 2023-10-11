package ru.liga.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.dto.response.OrderResponse;
import ru.liga.enums.StatusOrder;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class RestaurantController {
    @GetMapping
    public ResponseEntity<List<OrderResponse>> findOrdersByStatus(@RequestParam StatusOrder statusOrder) {
        //TODO
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
