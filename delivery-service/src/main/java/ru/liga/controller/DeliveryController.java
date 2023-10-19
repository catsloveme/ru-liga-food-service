package ru.liga.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.response.PageResponse;
import ru.liga.enums.StatusCourier;


@RestController
public class DeliveryController {
    @GetMapping("/deliveries")
    public ResponseEntity<PageResponse> findOrdersByStatus(@RequestParam StatusCourier status) {
        //TODO
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/delivery/{id}")
    public HttpStatus ChangeOrderStatusById(@PathVariable Long id,
                                            @RequestBody StatusCourier statusOrder) {
        //TODO if (delivery == null) throw new DeliveryNotFoundException(id);
        return HttpStatus.OK;

    }


}
