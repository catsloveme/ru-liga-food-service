package ru.liga.controller;

import java.math.BigDecimal;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.api.RestaurantMenuItemService;
import ru.liga.api.RestaurantService;
import ru.liga.clients.OrderFeign;
import ru.liga.dto.request.RestaurantMenuItemRequest;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.dto.response.RestaurantResponse;
import ru.liga.enums.StatusOrder;
import ru.liga.enums.StatusRestaurant;
import ru.liga.service.rabbitMQ.NotificationService;

/**
 * Контроллер ресторанов.
 */
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant-service")
public class RestaurantController {

    private final RestaurantService jpaRestaurantService;
    private final RestaurantMenuItemService jpaRestaurantMenuItemService;
    private final NotificationService notificationService;
    private final OrderFeign orderFeign;

    /**
     * Поиск ресторана по его id.
     *
     * @param id идентификатор ресторана
     * @return ответ ресторана
     */
    @Operation(summary = "Получить ресторан по его идентификатору")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "restaurant not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponse> findRestaurantById(
        @Parameter(description = "Идентификатор ресторана") @PathVariable Long id) {
        RestaurantResponse response = jpaRestaurantService.findRestaurantById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Поиск всех ресторанов.
     *
     * @return список ответов ресторанов
     */
    @Operation(summary = "Получить все рестораны")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "404", description = "restaurants not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping
    public ResponseEntity<List<RestaurantResponse>> findAllRestaurants() {
        List<RestaurantResponse> response = jpaRestaurantService.findAllRestaurants();
        return ResponseEntity.ok(response);
    }

    /**
     * Поиск блюда по id.
     * @param id дентификатор блюда
     * @return ответ блюда
     */
    @Operation(summary = "Получить блюдо по его идентификатору")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "restaurant menu item not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/menuItem/{id}")
    public ResponseEntity<RestaurantMenuItemResponse> findRestaurantMenuItemById(
        @Parameter(description = "Идентификатор части заказа") @PathVariable Long id) {
        RestaurantMenuItemResponse response = jpaRestaurantMenuItemService.findRestaurantMenuItemById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Добавить блюдо.
     *
     * @param request данные для запроса добавления блюда
     * @return ответ блюда
     */
    @Operation(summary = "Добавить новое блюдо в меню")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping("/menuItem")
    public ResponseEntity<RestaurantMenuItemResponse> addRestaurantMenuItem(
        @RequestBody RestaurantMenuItemRequest request
    ) {
        RestaurantMenuItemResponse response = jpaRestaurantMenuItemService.addRestaurantMenuItem(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Удаление блюда по его id.
     *
     * @param id идентификатор блюда
     * @return ResponseEntity
     */
    @Operation(summary = "Удалить блюдо из меню")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "restaurant menu item not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @DeleteMapping("/menuItem/{id}")
    public ResponseEntity<Void> deleteRestaurantMenuItemById(
        @Parameter(description = "Идентификатор блюда") @PathVariable Long id) {
        jpaRestaurantMenuItemService.deleteRestaurantMenuItemById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Обновление цены блюда.
     *
     * @param id идентификатор блюда
     * @param price новая цена блюда
     * @return ResponseEntity
     */
    @Operation(summary = "Обновить цену блюда по его идентификатору")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "restaurant menu item not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PatchMapping("/menuItem/{id}")
    public ResponseEntity<Void> updatePrice(
        @Parameter(description = "Идентификатор блюда") @PathVariable Long id,
        @RequestParam BigDecimal price) {
        jpaRestaurantMenuItemService.updatePrice(price, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Обновление статуса ресторана.
     *
     * @param id идентификатор ресторана
     * @param status статус ресторана
     * @return ResponseEntity
     */
    @Operation(summary = "Обновить статус ресторана по его идентификатору")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "restaurant not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PatchMapping("/restaurant/{id}")
    public ResponseEntity<Void> updateStatus(
        @Parameter(description = "Идентификатор ресторана") @PathVariable Long id,
        @RequestParam StatusRestaurant status) {
        jpaRestaurantService.changeStatusById(status, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Отклонение заказа и обновление статуса заказа.
     *
     * @param id идентификатор заказа
     * @return ResponseEntity
     */
    @Operation(summary = "Отклонить заказ")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "Order not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PatchMapping("/order/{id}/denied")
    public ResponseEntity<Void> denyOrder(
        @Parameter(description = "Идентификатор заказа") @PathVariable Long id) {
        return orderFeign.updateOrderStatus(id, StatusOrder.KITCHEN_DENIED);
    }

    /**
     * Приготовление заказа и обновдение статуса заказа.
     *
     * @param id идентификатор заказа
     * @return ResponseEntity
     */
    @Operation(summary = "Начать готовить заказ")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "Order not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PatchMapping("/order/{id}/preparing")
    public ResponseEntity<Void> preparingOrder(
        @Parameter(description = "Идентификатор заказа") @PathVariable Long id) {
        return orderFeign.updateOrderStatus(id, StatusOrder.KITCHEN_PREPARING);
    }

    /**
     * Возврат средств за заказ и обновление статуса заказа.
     *
     * @param id идентификатор заказа
     * @return ResponseEntity
     */
    @Operation(summary = "Возврат средств за заказ")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "Order not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PatchMapping("/order/{id}/refunded")
    public ResponseEntity<Void> refundOrder(
        @Parameter(description = "Идентификатор заказа") @PathVariable Long id) {
        return orderFeign.updateOrderStatus(id, StatusOrder.KITCHEN_REFUNDED);
    }

    /**
     * Завершение заказа, обновдение статуса заказа, отправление сообщения о поиске рурьеров.
     *
     * @param id идентификатор заказа
     * @return ResponseEntity
     */
    @Operation(summary = "Завершить приготовление заказа")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "Order not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PatchMapping("/order/{id}/finish")
    public ResponseEntity<Void> finishOrder(
        @Parameter(description = "Идентификатор заказа") @PathVariable Long id) {
        notificationService.sendCourierSearch(id);
        return orderFeign.updateOrderStatus(id, StatusOrder.KITCHEN_FINISHED);
    }

}
