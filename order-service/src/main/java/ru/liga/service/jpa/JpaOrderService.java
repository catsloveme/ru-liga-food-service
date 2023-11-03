package ru.liga.service.jpa;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.api.OrderService;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.dto.response.OrderResponse;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.dto.response.RestaurantResponse;
import ru.liga.entity.Customer;
import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;
import ru.liga.entity.Restaurant;
import ru.liga.enums.StatusOrder;
import ru.liga.exception.DataNotFoundException;
import ru.liga.mapping.CreateOrderMapper;
import ru.liga.mapping.OrderItemToMenuMapper;
import ru.liga.mapping.RestaurantMapper;
import ru.liga.repository.CustomerRepository;
import ru.liga.repository.OrderItemRepository;
import ru.liga.repository.OrderRepository;
import ru.liga.repository.RestaurantRepository;
import static ru.liga.enums.StatusOrder.CUSTOMER_CREATED;

/**
 * Сервис для работы с репозиторием jpa.
 */
@RequiredArgsConstructor
public class JpaOrderService implements OrderService {

    private final OrderRepository jpaOrderRepository;
    private final OrderItemRepository jpaOrderItemRepository;
    private final CustomerRepository jpaCustomerRepository;
    private final RestaurantRepository jpaRestaurantRepository;
    private final OrderItemToMenuMapper mapperOrderItem;
    private final RestaurantMapper mapperRestaurant;
    private final CreateOrderMapper mapperCreateOrder;

    /**
     * Поиск всех заказов.
     *
     * @return список ответов заказов
     */
    public List<OrderResponse> findAllOrders() {
        List<Order> orders = jpaOrderRepository.findAll();
        List<OrderResponse> responses = new ArrayList<>();
        for (Order order : orders) {
            responses.add(this.findOrderById(order.getId()));
        }
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
        OffsetDateTime time = order.getTimestamp();
        RestaurantResponse restaurantResponse = mapperRestaurant.toDto(order.getRestaurant());
        List<OrderItem> orderItems = jpaOrderItemRepository.findByOrderId(orderId);
        List<RestaurantMenuItemResponse> menuItems = mapperOrderItem.toDto(orderItems);

        return OrderResponse.builder()
            .id(orderId)
            .restaurant(restaurantResponse)
            .items(menuItems)
            .timestamp(time)
            .build();
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

