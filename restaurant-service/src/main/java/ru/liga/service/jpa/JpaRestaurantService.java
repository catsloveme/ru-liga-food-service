package ru.liga.service.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.api.RestaurantService;
import ru.liga.dto.response.RestaurantResponse;
import ru.liga.entity.Order;
import ru.liga.entity.Restaurant;
import ru.liga.enums.StatusOrder;
import ru.liga.enums.StatusRestaurant;
import ru.liga.exception.NotFoundException;
import ru.liga.mapping.RestaurantMapper;
import ru.liga.repository.OrderRepository;
import ru.liga.repository.RestaurantRepository;

/**
 * Сервис для работы с репозиторием jpa.
 */
@Service
@RequiredArgsConstructor
public class JpaRestaurantService implements RestaurantService {

    private final RestaurantRepository jpaRestaurantRepository;
    private final OrderRepository jpaOrderRepository;
    private final RestaurantMapper mapper;

    /**
     * Поиск ресторана по его id.
     *
     * @param id идентификатор ресторана
     * @return ответ ресторана
     */
    public RestaurantResponse findRestaurantById(Long id) {
        Restaurant restaurant = jpaRestaurantRepository.findById(id).orElseThrow(() ->
            new NotFoundException(String.format("Restaurant menu item id = %d not found", id)));
        return mapper.toDto(restaurant);
    }

    /**
     * Изменение стратуса ресторана.
     *
     * @param status       статус ресторана
     * @param restaurantId идентификкатор ресторана
     */

    public void changeStatusById(StatusRestaurant status, Long restaurantId) {
        jpaRestaurantRepository.updateRestaurantStatus(status, restaurantId);
    }

    /**
     * Обновление статуса закза по его id.
     *
     * @param status  статус заказа
     * @param orderId идентификатор заказа
     */

    public void updateOrderStatus(StatusOrder status, Long orderId) {
        jpaOrderRepository.updateOrderStatus(status, orderId);
    }

    /**
     * Обновление id курьера у заказа, найденного по его id.
     *
     * @param courierId идентификатор курьера
     * @param orderId   идентификатор заказа
     */

    public void updateCourierId(Long courierId, Long orderId) {
        jpaOrderRepository.updateCourierId(courierId, orderId);
    }

    /**
     * Поиск адреса заказчика по id заказа.
     *
     * @param orderId идентификатор заказа
     * @return строковое представление адреса заказчика
     */
    public String findAddressCustomerByOrderId(Long orderId) {
        Order order = jpaOrderRepository.findById(orderId).orElseThrow(() ->
            new NotFoundException(String.format("Order id = %d not found", orderId)));
        String addressCustomer = order.getCustomer().getAddress();
        return addressCustomer;
    }

    /**
     * Поиск адреса заказчика по id заказа.
     *
     * @param orderId идентификатор заказа
     * @return строковое представление адреса заказчика
     */
    public String findAddressRestaurantByOrderId(Long orderId) {
        Order order = jpaOrderRepository.findById(orderId).orElseThrow(() ->
            new NotFoundException(String.format("Order id = %d not found", orderId)));
        String addressRestaurant = order.getRestaurant().getAddress();
        return addressRestaurant;
    }
}
