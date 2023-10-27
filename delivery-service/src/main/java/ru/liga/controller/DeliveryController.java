package ru.liga.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.api.CourierService;
import ru.liga.dto.response.CourierResponse;
import ru.liga.enums.StatusCourier;


import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class DeliveryController {

    private final CourierService courierService;

    @GetMapping("/deliveries")
    public ResponseEntity<List<CourierResponse>> findCourierByStatus(@RequestParam StatusCourier status) {
        List<CourierResponse> response = courierService.findByStatus(status);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delivery/{id}")
    public ResponseEntity<Void> changeCourierStatusById(@PathVariable Long id,
                                                        @RequestParam StatusCourier status) {
        log.info("change couriers by status: {}",status);
        courierService.changeOrderStatusById(id, status);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
