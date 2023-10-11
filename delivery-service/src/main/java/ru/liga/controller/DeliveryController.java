package ru.liga.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.response.DeliveryResponse;
import ru.liga.enums.StatusOrder;

import java.util.List;

@RestController
public class DeliveryController {
    @GetMapping("/deliveries")
    public ResponseEntity<List<DeliveryResponse>> findOrdersByStatus(@RequestParam StatusOrder status) {
        //TODO
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/delivery/{id}")
    public HttpStatus ChangeOrderStatusById(@PathVariable Long id,
                                            @RequestBody StatusOrder statusOrder){
        //TODO if (delivery == null) throw new DeliveryNotFoundException(id);
        return HttpStatus.OK;

    }


}
