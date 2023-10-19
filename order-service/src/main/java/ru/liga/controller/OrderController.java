package ru.liga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.request.RequestCreatingOrder;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.dto.response.OrderResponse;
import ru.liga.service.JpaOrderService;

import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private JpaOrderService orderService;
    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAllOrders(){
        List<OrderResponse> ordersResponse = orderService.findAllOrders();
        return ResponseEntity.ok(ordersResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findOrderById(@PathVariable Long id){
        OrderResponse orderResponse = orderService.findOrderById(id);
        return ResponseEntity.ok(orderResponse);
    }
    @PostMapping
    public ResponseEntity<CreateOrderResponse> createNewOrder(@RequestBody RequestCreatingOrder requestCreatingOrder){
        //TODO
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
