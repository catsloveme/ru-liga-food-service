package ru.liga.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.request.RequestCreatingOrder;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.dto.response.OrderResponse;
import ru.liga.service.JpaOrderService;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private JpaOrderService orderService;
    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAllOrders(){
        List<OrderResponse> ordersResponse = orderService.findAllOrders();
        log.info("find all orders: {}", ordersResponse);
        return ResponseEntity.ok(ordersResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findOrderById(@PathVariable Long id){
        OrderResponse orderResponse = orderService.findOrderById(id);
        return ResponseEntity.ok(orderResponse);
    }
    @PostMapping
    public ResponseEntity<CreateOrderResponse> createNewOrder(@RequestBody RequestCreatingOrder requestCreatingOrder){
        CreateOrderResponse response = orderService.createNewOrder(requestCreatingOrder, 1L);
        return ResponseEntity.ok(response);
    }

}
