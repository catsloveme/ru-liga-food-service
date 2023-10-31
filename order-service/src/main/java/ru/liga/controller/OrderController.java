package ru.liga.controller;

import java.util.List;
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
import ru.liga.api.OrderItemService;
import ru.liga.api.OrderService;
import ru.liga.dto.request.CreateOrderItemRequest;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.dto.response.OrderItemResponse;
import ru.liga.dto.response.OrderResponse;
import ru.liga.enums.StatusOrder;

/**
 * Контроллер заказа.
 */
@Log4j2
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final OrderItemService orderItemService;

    /**
     * Поиск всех заказов.
     *
     * @return список ответов всех заказов
     */
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
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findOrderById(@PathVariable Long id) {
        OrderResponse orderResponse = orderService.findOrderById(id);
        return ResponseEntity.ok(orderResponse);
    }

    /**
     * Поиск всех частей всех заказов.
     *
     * @return список ответов частей заказоы
     */
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
    @GetMapping("/orderItems/{id}")
    public ResponseEntity<OrderItemResponse> findOrderItemById(@PathVariable Long id) {
        OrderItemResponse orderItemResponse = orderItemService.findOrderItemById(id);
        return ResponseEntity.ok(orderItemResponse);
    }

    /**
     * Создание заказа.
     *
     * @param requestCreatingOrder данные для запроса по созданию заказа
     * @return ответ создания заказа
     */
    @PostMapping
    public ResponseEntity<CreateOrderResponse> addOrder(@RequestBody CreateOrderRequest requestCreatingOrder) {
        CreateOrderResponse response = orderService.addOrder(requestCreatingOrder);

        return ResponseEntity.ok(response);
    }

    /**
     * Создание части заказа.
     *
     * @param requestCreatingOrder данные для запроса по созданию части заказа
     * @return ответ части заказа
     */
    @PostMapping("/orderItem")
    public ResponseEntity<OrderItemResponse> addOrderItem(@RequestBody CreateOrderItemRequest requestCreatingOrder) {
        OrderItemResponse response = orderItemService.addOrderItem(requestCreatingOrder);

        return ResponseEntity.ok(response);
    }

    /**
     * Обновление id курьера у заказа, найденного по его id.
     *
     * @param courierId идентификатор курьера
     * @param orderId   идентификатор заказа
     * @return ResponseEntity
     */
    @PatchMapping("/courier/{courierId}/order/{orderId}")
    ResponseEntity<Void> updateCourierId(@PathVariable Long courierId, @PathVariable Long orderId) {
        orderService.updateCourierId(courierId, orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Обновление статуса заказа по его id.
     *
     * @param orderId идентификатор заказа
     * @param status  статус заказа
     * @return ResponseEntity
     */
    @PatchMapping("/{orderId}")
    ResponseEntity<Void> updateOrderStatus(@PathVariable Long orderId, @RequestParam StatusOrder status) {
        orderService.updateOrderStatus(status, orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

