package ru.liga.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.api.CustomerService;
import ru.liga.clients.OrderFeign;
import ru.liga.dto.request.CreateOrderItemRequest;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.dto.response.CustomerResponse;
import ru.liga.dto.response.OrderItemResponse;
import ru.liga.dto.response.OrderResponse;
import ru.liga.enums.StatusOrder;
import ru.liga.service.rabbitMQ.OrderService;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {


    private final CustomerService customerService;
    private final OrderFeign orderFeign;
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAllOrders() {
        List<CustomerResponse> customerResponses = customerService.findAllCustomers();
        log.info("find all customers: {}", customerResponses);
        return ResponseEntity.ok(customerResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findOrderById(@PathVariable Long id) {
        CustomerResponse customerResponse = customerService.findCustomerById(id);
        return ResponseEntity.ok(customerResponse);
    }
//    @PostMapping("/order")
//    public ResponseEntity<CreateOrderResponse> addOrder(@RequestBody CreateOrderRequest requestCreatingOrder) {
//        return orderFeign.addOrder(requestCreatingOrder);
//    }
    @PostMapping("/order/create")
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderRequest requestCreatingOrder){
        customerService.updateStatusOrder(StatusOrder.CUSTOMER_CREATED);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("/order/pay")
    public ResponseEntity<Void> payOrder(){
        customerService.updateStatusOrder(StatusOrder.CUSTOMER_PAID);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("/order/cancel")
    public ResponseEntity<Void> cancelOrder(){
        customerService.updateStatusOrder(StatusOrder.CUSTOMER_CANCELLED);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
