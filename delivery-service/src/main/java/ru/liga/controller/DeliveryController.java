package ru.liga.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.api.CourierService;
import ru.liga.dto.response.CourierResponse;
import ru.liga.enums.StatusCourier;

/**
 * Контроллер курьеров.
 */
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/couriers")
public class DeliveryController {

    private final CourierService courierService;

    /**
     * Поиск курьеров по статусу.
     * @param status статус курьера
     * @return список ответов курьеров
     */
    @GetMapping
    public ResponseEntity<List<CourierResponse>> findCourierByStatus(@RequestParam StatusCourier status) {
        List<CourierResponse> response = courierService.findByStatus(status);
        return ResponseEntity.ok(response);
    }

    /**
     * Изменение статуса курьера по его id.
     * @param id идентификатор курьера
     * @param status статус курьера
     * @return ResponseEntity
     */
    @PostMapping("{id}")
    public ResponseEntity<Void> changeCourierStatusById(
        @PathVariable Long id,
        @RequestParam StatusCourier status
    ) {
        log.info("change couriers by status: {}", status);
        courierService.changeOrderStatusById(id, status);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
