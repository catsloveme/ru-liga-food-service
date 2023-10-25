package ru.liga.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.api.CustomerService;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CustomerResponse;
import ru.liga.service.rabbitMQ.OrderService;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
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
    @PostMapping("/order/create")
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderRequest request){
        orderService.createOrder(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
