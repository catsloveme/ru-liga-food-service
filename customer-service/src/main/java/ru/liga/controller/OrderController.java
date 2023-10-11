package ru.liga.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.request.RequestCreatingOrder;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.dto.response.OrderResponse;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAllOrders(){
        //TODO
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<OrderResponse> findOrderById(@PathVariable Long id){
        //TODO
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CreateOrderResponse> createNewOrder(@RequestBody RequestCreatingOrder requestCreatingOrder){
        //TODO
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
