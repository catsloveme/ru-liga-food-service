package ru.liga.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.api.CourierService;
import ru.liga.dto.response.OrderResponse;
import ru.liga.enums.StatusCourier;
import ru.liga.service.rabbitMQ.NotificationService;

/**
 * Контроллер курьеров.
 */
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/delivery-service")
@Tag(name = "API работы с курьерами")
public class DeliveryController {

    private final CourierService courierService;
    private final NotificationService notificationService;

    /**
     * Поиск готовых заказов.
     *
     * @return список ответов заказов
     */
    @Operation(summary = "Найти готовые заказы")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "Courier not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> findFinishOrder() {
        log.info("Поиск готовых заказов");
        return ResponseEntity.ok(courierService.findFinishOrder());

    }

    /**
     * Завершение доставки курьером.
     *
     * @param orderId   идентификатор заказа
     * @param courierId идентификатор курьера
     */
    @Operation(summary = "Завершить заказ")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "Courier not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping("/finishOrder/{orderId}/courier/{courierId}")
    public ResponseEntity<Void> finishDelivery(@PathVariable Long orderId, @PathVariable Long courierId) {
        log.info("Завершение доставки заказа {},в ресторан отправлено уведомление через notification сервис", orderId);
        String MESSAGE_DELIVERED = "Заказ {}  успешно доставлен";
        notificationService.sendMessageFinish(orderId, MESSAGE_DELIVERED);
        courierService.changeStatusById(courierId, StatusCourier.DELIVERY_COMPLETE);
        courierService.changeStatusById(courierId, StatusCourier.DELIVERY_DENIED);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
