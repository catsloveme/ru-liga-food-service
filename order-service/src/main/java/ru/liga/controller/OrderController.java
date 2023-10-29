package ru.liga.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.request.CreateOrderItemRequest;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.OrderItemResponse;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.dto.response.OrderResponse;
import ru.liga.api.OrderItemService;
import ru.liga.api.OrderService;
import ru.liga.enums.StatusOrder;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAllOrders() {
        List<OrderResponse> ordersResponse = orderService.findAllOrders();
        log.info("find all orders: {}", ordersResponse);
        return ResponseEntity.ok(ordersResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findOrderById(@PathVariable Long id) {
        OrderResponse orderResponse = orderService.findOrderById(id);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/orderItems")
    public ResponseEntity<List<OrderItemResponse>> findAllOrderItems() {
        List<OrderItemResponse> ordersResponse = orderItemService.findAllOrderItems();
        return ResponseEntity.ok(ordersResponse);
    }

    @GetMapping("/orderItems/{id}")
    public ResponseEntity<OrderItemResponse> findOrderItemById(@PathVariable Long id) {
        OrderItemResponse orderItemResponse = orderItemService.findOrderItemById(id);
        return ResponseEntity.ok(orderItemResponse);
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> addOrder(@RequestBody CreateOrderRequest requestCreatingOrder) {
        CreateOrderResponse response = orderService.addOrder(requestCreatingOrder);

        return ResponseEntity.ok(response);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteOrderById(@PathVariable Long orderId) {
//        orderService.deleteOrderById(orderId);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @PostMapping("/orderItem")
    public ResponseEntity<OrderItemResponse> addOrderItem(@RequestBody CreateOrderItemRequest requestCreatingOrder) {
        OrderItemResponse response = orderItemService.addOrderItem(requestCreatingOrder);

        return ResponseEntity.ok(response);
    }

//    @DeleteMapping("/orderItem/{id}")
//    public ResponseEntity<Void> deleteOrderItemById(@PathVariable Long orderItemId) {
//        orderItemService.deleteOrderItemById(orderItemId);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @PatchMapping("/courier/{courierId}/order/{orderId}")
    ResponseEntity<Void> updateCourierId(@PathVariable Long courierId, @PathVariable Long orderId) {
        orderService.updateCourierId(courierId, orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{orderId}")
    ResponseEntity<Void> updateOrderStatus(@PathVariable Long orderId, @RequestParam StatusOrder status) {
        orderService.updateOrderStatus(status, orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

