package ru.liga.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.liga.api.RestaurantMenuItemService;
import ru.liga.api.RestaurantService;
import ru.liga.dto.request.RestaurantMenuItemRequest;
import ru.liga.dto.response.RestaurantByStatusResponse;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.dto.response.RestaurantResponse;
import ru.liga.enums.StatusRestaurant;
import java.math.BigDecimal;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService jpaRestaurantService;

    private final RestaurantMenuItemService jpaRestaurantMenuItemService;


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
}
