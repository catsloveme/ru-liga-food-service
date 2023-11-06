package ru.liga.service.jpa;

import java.time.OffsetDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.api.OrderService;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.dto.response.OrderResponse;
import ru.liga.entity.Customer;
import ru.liga.entity.Order;
import ru.liga.entity.Restaurant;
import ru.liga.enums.StatusOrder;
import ru.liga.exception.DataNotFoundException;
import ru.liga.mapping.CreateOrderMapper;
import ru.liga.mapping.OrderMapper;
import ru.liga.repository.CustomerRepository;
import ru.liga.repository.OrderRepository;
import ru.liga.repository.RestaurantRepository;
import static ru.liga.enums.StatusOrder.CUSTOMER_CREATED;

/**
 * Сервис для работы с репозиторием jpa.
 */
@RequiredArgsConstructor
public class JpaOrderService implements OrderService {

    private final OrderRepository jpaOrderRepository;
    private final CustomerRepository jpaCustomerRepository;
    private final RestaurantRepository jpaRestaurantRepository;
    private final CreateOrderMapper mapperCreateOrder;
    private final OrderMapper mapperOrder;

    /**
     * Поиск всех заказов.
     *
     * @return список ответов заказов
     */
    public List<OrderResponse> findAllOrders() {
        List<Order> orders = jpaOrderRepository.findAll();
        List<OrderResponse> responses = mapperOrder.toDtos(orders);
        return responses;
    }

    /**
     * Поиск заказа по его id.
     *
     * @param orderId идентификатор заказа
     * @return ответ заказа
     */
    public OrderResponse findOrderById(Long orderId) {
        Order order = jpaOrderRepository.findOrderById(orderId);
        OrderResponse response = mapperOrder.toDto(order);
        return response;
    }

    /**
     * Поиск истории заказа по id заказчика.
     *
     * @param customerId идентификатор заказчика
     * @return ответ заказа
     */
    public List<OrderResponse> findOrdersByCustomerId(Long customerId) {
        List<Order> orders = jpaOrderRepository.findOrderByCustomerId(customerId);
        List<OrderResponse> responses = mapperOrder.toDtos(orders);
        return responses;
    }

    /**
     * Создание заказа.
     *
     * @param createOrderRequest данные для запроса на создание заказа
     * @return ответ создания заказа
     */
    public CreateOrderResponse addOrder(CreateOrderRequest createOrderRequest) {
        Order order = new Order();

        Long customerId = createOrderRequest.getCustomerId();

        Customer customer = jpaCustomerRepository.findById(customerId).orElseThrow(() ->
            new DataNotFoundException(String.format("Customer id = %d not found", customerId)));

        order.setCustomer(customer);
        order.setStatus(CUSTOMER_CREATED);

        Long restaurantId = createOrderRequest.getRestaurantId();

        Restaurant restaurant = jpaRestaurantRepository.findById(restaurantId).orElseThrow(() ->
            new DataNotFoundException(String.format("Restaurant id = %d not found", restaurantId)));

        order.setRestaurant(restaurant);
        order.setTimestamp(OffsetDateTime.now().plusHours(1L));
        jpaOrderRepository.saveAndFlush(order);
        return mapperCreateOrder.toDto(order);
    }

    /**
     * Обновление id курьера у заказа, найденного по его id.
     *
     * @param courierId идентификатор курьера
     * @param orderId   идентификатор заказа
     */
    @Transactional
    public void updateCourierId(Long courierId, Long orderId) {
        jpaOrderRepository.updateCourierId(courierId, orderId);
    }

    /**
     * Обновление статуса закза по его id.
     *
     * @param status  статус заказа
     * @param orderId идентификатор заказа
     */
    @Transactional
    public void updateOrderStatus(StatusOrder status, Long orderId) {
        jpaOrderRepository.updateOrderStatus(status, orderId);
    }

}

