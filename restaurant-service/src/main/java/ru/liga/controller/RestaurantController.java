package ru.liga.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.liga.api.RestaurantMenuItemService;
import ru.liga.api.RestaurantService;
import ru.liga.clients.OrderFeign;
import ru.liga.dto.request.RestaurantMenuItemRequest;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.dto.response.RestaurantResponse;
import ru.liga.enums.StatusOrder;
import ru.liga.enums.StatusRestaurant;
import ru.liga.service.rabbitMQ.NotificationService;

import java.math.BigDecimal;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService jpaRestaurantService;

    private final RestaurantMenuItemService jpaRestaurantMenuItemService;
    private final NotificationService courierService;
    private final OrderFeign orderFeign;


    @GetMapping("restaurant/{id}")
    public ResponseEntity<RestaurantResponse> findRestaurantById(@PathVariable Long id) {
        RestaurantResponse response = jpaRestaurantService.findRestaurantById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantResponse>> findAllRestaurants() {
        List<RestaurantResponse> response = jpaRestaurantService.findAllRestaurants();
        return ResponseEntity.ok(response);
    }


    @GetMapping("menuItem/{id}")
    public ResponseEntity<RestaurantMenuItemResponse> findRestaurantMenuItemById(@PathVariable Long id) {
        RestaurantMenuItemResponse response = jpaRestaurantMenuItemService.findRestaurantMenuItemById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/menuItem")
    public ResponseEntity<RestaurantMenuItemResponse> addRestaurantMenuItem(@RequestBody RestaurantMenuItemRequest request) {
        RestaurantMenuItemResponse response = jpaRestaurantMenuItemService.addRestaurantMenuItem(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/menuItem/{id}")
    public ResponseEntity<Void> deleteRestaurantMenuItemById(@PathVariable Long id) {
        jpaRestaurantMenuItemService.deleteRestaurantMenuItemById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/menuItem/{id}")
    public ResponseEntity<Void> updatePrice(@PathVariable Long id, @RequestParam BigDecimal price) {
        jpaRestaurantMenuItemService.updatePrice(price, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/restaurant/{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestParam StatusRestaurant status) {
        jpaRestaurantService.changeOrderStatusById(status, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PatchMapping("/order/{id}/denied")
    public ResponseEntity<Void> denyOrder(@PathVariable Long id) {
        return orderFeign.updateOrderStatus(id, StatusOrder.KITCHEN_DENIED);
    }

    @PatchMapping("/order/{id}/preparing")
    public ResponseEntity<Void> preparingOrder(@PathVariable Long id) {
        return orderFeign.updateOrderStatus(id, StatusOrder.KITCHEN_PREPARING);
    }

    @PatchMapping("/order/{id}/refunded")
    public ResponseEntity<Void> refundOrder(@PathVariable Long id) {
        return orderFeign.updateOrderStatus(id, StatusOrder.KITCHEN_REFUNDED);
    }

    @PatchMapping("/order/{id}/finish")
    public ResponseEntity<Void> finishOrder(@PathVariable Long id) {
        courierService.sendCourierSearch(id);
        log.info("The search for couriers has begun");
        return orderFeign.updateOrderStatus(id, StatusOrder.KITCHEN_FINISHED);
    }

}