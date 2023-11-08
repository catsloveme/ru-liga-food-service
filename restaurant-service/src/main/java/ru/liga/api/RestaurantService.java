package ru.liga.api;

import java.util.UUID;
import ru.liga.dto.response.RestaurantResponse;
import ru.liga.enums.StatusOrder;
import ru.liga.enums.StatusRestaurant;

/**
 * Интерфейс описания api сервиса Restaurant.
 */
public interface RestaurantService {

    /**
     * Поиск ресторана по его id.
     *
     * @param id идентификатор ресторана
     * @return ответ ресторана
     */
    RestaurantResponse findRestaurantById(UUID id);

    /**
     * Изменение статуса ресторана по его id.
     *
     * @param status       статус ресторана
     * @param restaurantId идентификкатор ресторана
     */
    void changeStatusById(StatusRestaurant status, UUID restaurantId);

    /**
     * Обновление статуса заказа по его id.
     *
     * @param status  статус заказа
     * @param orderId идентификатор заказа
     */
    void updateOrderStatus(StatusOrder status, UUID orderId);

    /**
     * Обновление id курьера у заказа, найденного по его id.
     *
     * @param courierId идентификатор курьера
     * @param orderId   идентификатор заказа
     */
    void updateCourierId(UUID courierId, UUID orderId);

    /**
     * Поиск адреса заказчика по id заказа.
     *
     * @param orderId идентификатор заказа
     * @return строковое представление адреса заказчика
     */
    String findAddressCustomerByOrderId(UUID orderId);

    /**
     * Поиск адреса заказчика по id заказа.
     *
     * @param orderId идентификатор заказа
     * @return строковое представление адреса заказчика
     */
    String findAddressRestaurantByOrderId(UUID orderId);
}
