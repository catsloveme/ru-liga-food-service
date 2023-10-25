package ru.liga.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.liga.api.RestaurantMenuItemService;
import ru.liga.api.RestaurantService;
import ru.liga.clients.CourierFeign;
import ru.liga.clients.OrderFeign;
import ru.liga.dto.request.RestaurantMenuItemRequest;
import ru.liga.dto.response.CourierResponse;
import ru.liga.dto.response.RestaurantByStatusResponse;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.dto.response.RestaurantResponse;
import ru.liga.enums.StatusCourier;
import ru.liga.enums.StatusOrder;
import ru.liga.enums.StatusRestaurant;
import ru.liga.service.rabbitMQ.CourierService;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService jpaRestaurantService;

    private final RestaurantMenuItemService jpaRestaurantMenuItemService;
    private final CourierService courierService;
    private final CourierFeign courierFeign;
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

    @GetMapping("/orders")
    public ResponseEntity<List<RestaurantByStatusResponse>> findRestaurantsByStatus(@RequestParam StatusRestaurant status) {
        List<RestaurantByStatusResponse> response = jpaRestaurantService.findRestaurantsByStatus(status);
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
    public ResponseEntity<Void> updatePrice(@PathVariable Long id, @RequestParam BigDecimal price){
        jpaRestaurantMenuItemService.updatePrice(price, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/restaurant/{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestParam StatusRestaurant status){
        jpaRestaurantService.changeOrderStatusById(status,id);
        courierService.sendStatusAccept(status);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/deliveries")
    public ResponseEntity<List<CourierResponse>> findCourierByStatus(@RequestParam StatusCourier status) {
       return courierFeign.findCourierByStatus(status);

    }
    @PatchMapping("/order/accepted/{id}")
    public ResponseEntity<Void> acceptOrder(@PathVariable Long id){
        return orderFeign.updateStatus(id, StatusOrder.KITCHEN_ACCEPTED);
    }

    @PatchMapping("/order/denied/{id}")
    public ResponseEntity<Void> denyOrder(@PathVariable Long id){
        return orderFeign.updateStatus(id, StatusOrder.KITCHEN_DENIED);
    }
    @PatchMapping("/order/preparing/{id}")
    public ResponseEntity<Void> preparingOrder(@PathVariable Long id){
        return orderFeign.updateStatus(id, StatusOrder.KITCHEN_PREPARING);
    }
    @PatchMapping("/order/refunded/{id}")
    public ResponseEntity<Void> refundOrder(@PathVariable Long id){
        return orderFeign.updateStatus(id, StatusOrder.KITCHEN_REFUNDED);}

    @PatchMapping("/order/finish/{id}")
    public ResponseEntity<Void> finishOrder(@PathVariable Long id){

        return orderFeign.updateStatus(id, StatusOrder.KITCHEN_FINISHED);
    }

}
//    @PatchMapping("/restaurant/{id}")
//    ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestParam StatusRestaurant status);
//}KITCHEN_ACCEPTED,
//    KITCHEN_DENIED,
//    KITCHEN_PREPARING,
//    KITCHEN_REFUNDED
//     restaurantFeign.updateStatus(requestCreatingOrder.getRestaurantId(), StatusRestaurant.KITCHEN_ACCEPTED);
