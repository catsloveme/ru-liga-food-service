package ru.liga.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.api.OrderItemService;
import ru.liga.api.OrderService;
import ru.liga.dto.request.CreateOrderItemRequest;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.dto.response.OrderItemResponse;
import ru.liga.dto.response.OrderResponse;
import ru.liga.service.rabbitMQ.NotificationService;

/**
 * Контроллер заказа.
 */

@Tag(name = "API работы с заказом")
@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final OrderItemService orderItemService;
    private final NotificationService notificationService;

    /**
     * Поиск всех заказов.
     *
     * @return список ответов всех заказов
     */
    @Operation(summary = "Получить все заказы")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "404", description = "Orders not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAllOrders() {
        List<OrderResponse> ordersResponse = orderService.findAllOrders();
        log.info("find all orders: {}", ordersResponse);
        return ResponseEntity.ok(ordersResponse);
    }

    /**
     * Поиск заказа по его id.
     *
     * @param id идентификатор заказа
     * @return ответ заказа
     */
    @Operation(summary = "Получить заказ по идентификатору")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "Order not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findOrderById(
        @Parameter(description = "Идентификатор заказа") @Min(1L) @PathVariable UUID id
    ) {
        OrderResponse orderResponse = orderService.findOrderById(id);
        return ResponseEntity.ok(orderResponse);
    }

    /**
     * Поиск истории заказов по id заказчика.
     *
     * @param id идентификатор заказа
     * @return ответ заказа
     */
    @Operation(summary = "Получить заказы по идентификатору заказччика")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderResponse>> findOrderByCustomer(
        @Parameter(description = "Идентификатор заказчика") @Min(1L) @PathVariable UUID id
    ) {
        List<OrderResponse> orderResponses = orderService.findOrdersByCustomerId(id);
        return ResponseEntity.ok(orderResponses);
    }

    /**
     * Поиск всех частей всех заказов.
     *
     * @return список ответов частей заказоы
     */
    @Operation(summary = "Получить все части заказов")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "404", description = "Order items not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/orderItems")
    public ResponseEntity<List<OrderItemResponse>> findAllOrderItems() {
        List<OrderItemResponse> ordersResponse = orderItemService.findAllOrderItems();
        return ResponseEntity.ok(ordersResponse);
    }

    /**
     * Поиск части заказа по его id.
     *
     * @param id идентификатор части заказа
     * @return ответ части заказа
     */
    @Operation(summary = "Получить часть заказа по его идентификатору")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "Order item not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/orderItems/{id}")
    public ResponseEntity<OrderItemResponse> findOrderItemById(
        @Parameter(description = "Идентификатор заказа") @Min(1L) @PathVariable UUID id
    ) {
        OrderItemResponse orderItemResponse = orderItemService.findOrderItemById(id);
        return ResponseEntity.ok(orderItemResponse);
    }

    /**
     * Создание заказа.
     *
     * @param requestCreateOrder данные для запроса по созданию заказа
     * @return ответ создания заказа
     */
    @Operation(summary = "Создать новый заказ")
    @ApiResponse(responseCode = "200", description = "Order created successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping
    public ResponseEntity<CreateOrderResponse> addOrder(@RequestBody CreateOrderRequest requestCreateOrder) {
        CreateOrderResponse response = orderService.addOrder(requestCreateOrder);
        UUID orderId = response.getId();
        String MESSAGE_CREATE_ORDER = "Заказ {} успешно создан. Необходимо его принять или отменить заказ.";
        notificationService.sendCreateOrder(orderId, MESSAGE_CREATE_ORDER);
        return ResponseEntity.ok(response);
    }

    /**
     * Добавдение части заказа.
     *
     * @param requestCreatingOrder данные для запроса по созданию части заказа
     * @return ответ части заказа
     */
    @Operation(summary = "Добавить часть заказа")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping("/orderItem")
    public ResponseEntity<OrderItemResponse> addOrderItem(@RequestBody CreateOrderItemRequest requestCreatingOrder) {
        OrderItemResponse response = orderItemService.addOrderItem(requestCreatingOrder);

        return ResponseEntity.ok(response);
    }


}

