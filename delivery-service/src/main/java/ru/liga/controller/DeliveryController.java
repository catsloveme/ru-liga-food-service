package ru.liga.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.response.CourierResponse;
import ru.liga.enums.StatusCourier;
import ru.liga.service.CourierService;

import java.util.List;

@Log4j2
@RestController
public class DeliveryController {
    @Autowired
    CourierService courierService;

    @GetMapping("/deliveries")
    public ResponseEntity<List<CourierResponse>> findCourierByStatus(@RequestParam StatusCourier status) {
        List<CourierResponse> response = courierService.findByStatus(status);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delivery/{id}")
    public ResponseEntity<Void> changeCourierStatusById(@PathVariable Long id,
                                                      @RequestBody StatusCourier status) {
        log.info("change couriers by status: {}",status);
        courierService.changeOrderStatusById(id, status);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
