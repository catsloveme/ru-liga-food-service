package ru.liga.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import ru.liga.dto.request.RestaurantMenuItemRequest;
import ru.liga.dto.response.RestaurantMenuItemResponse;
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
@Tag(name = "API работы с ресторанами и меню")
public class RestaurantController {

    private static final String MESSAGE_ACCEPT = "Заказ номер {} принят в работу и начал готовиться";
    private static final String MESSAGE_DENY = "Заказ номер {} отменен. Нет доставки в данный регион.";
    private static final String MESSAGE_SEARCH_COURIER = "Заказ номер {} готов. Осуществляется поиск курьера.";
    private final RestaurantService restaurantService;
    private final NotificationService notificationService;
    private final RestaurantMenuItemService restaurantMenuItemService;



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

        @Parameter(description = "Идентификатор части заказа") @PathVariable Long id
    ) {

        RestaurantMenuItemResponse response = restaurantMenuItemService.findRestaurantMenuItemById(id);
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
        RestaurantMenuItemResponse response = restaurantMenuItemService.addRestaurantMenuItem(request);
        return ResponseEntity.ok(response);
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

        @RequestParam BigDecimal price
    ) {

        restaurantMenuItemService.updatePrice(price, id);
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

        @RequestParam StatusRestaurant status
    ) {

        restaurantService.changeStatusById(status, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Принятие заказа, обновление статуса заказа, оповещение заказчика.
     *
     * @param id идентификатор заказа
     * @return ResponseEntity
     */
    @Operation(summary = "Принять заказ")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "Order not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PatchMapping("/order/{id}/accept")
    public ResponseEntity<Void> acceptOrder(
        @Parameter(description = "Идентификатор заказа") @PathVariable Long id
    ) {
        restaurantService.updateOrderStatus(StatusOrder.KITCHEN_PREPARING, id);
        notificationService.sendMessageOrder(MESSAGE_ACCEPT, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Отклонение заказа, обновление статуса заказа, оповещение заказчика
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
        @Parameter(description = "Идентификатор заказа") @PathVariable Long id
    ) {
        restaurantService.updateOrderStatus(StatusOrder.KITCHEN_DENIED, id);
        notificationService.sendMessageOrder(MESSAGE_DENY, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Завершение заказа, обновдение статуса заказа,уведомление заказчика и отправление сообщения о поиске курьеров.
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

        @Parameter(description = "Идентификатор заказа") @PathVariable Long id
    ) {
        restaurantService.updateOrderStatus(StatusOrder.KITCHEN_FINISHED, id);
        notificationService.sendMessageOrder(MESSAGE_SEARCH_COURIER, id);
        String addressRestaurant = restaurantService.findAddressRestaurantByOrderId(id);
        notificationService.sendCourierSearch(id, addressRestaurant);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
