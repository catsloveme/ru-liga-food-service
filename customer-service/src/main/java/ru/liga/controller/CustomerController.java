package ru.liga.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.api.CustomerService;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CustomerResponse;
import ru.liga.service.rabbitMQ.OrderService;

/**
 * Контроллер заказчика.
 */
@Log4j2
@Tag(name = "API работы с заказом со стороны заказчика")
@RestController
@RequestMapping("/customer-service")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;

    /**
     * Поиск всех заказов.
     *
     * @return список ответов для заказа
     */
    @Operation(summary = "Получить все заказы")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "404", description = "Orders not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAllOrders() {
        List<CustomerResponse> customerResponses = customerService.findAllCustomers();
        log.info("find all customers: {}", customerResponses);
        return ResponseEntity.ok(customerResponses);
    }

    /**
     * Поиск заказа по id.
     *
     * @param id идентификатор заказа
     * @return ответ для заказа
     */
    @Operation(summary = "Получить заказ по идентификатору")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "Order not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findOrderById(
        @Parameter(description = "Идентификатор заказа") @PathVariable Long id
    ) {
        CustomerResponse customerResponse = customerService.findCustomerById(id);
        return ResponseEntity.ok(customerResponse);
    }

    /**
     * Создание заказа.
     *
     * @param request запрос заказа
     * @return ответ для заказа
     */
    @Operation(summary = "Создать новый заказ")
    @ApiResponse(responseCode = "200", description = "Order created successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping("/order/create")
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderRequest request) {
        orderService.createOrder(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
