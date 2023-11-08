package ru.liga.api;

import java.util.List;
import ru.liga.dto.response.CourierResponse;
import ru.liga.dto.response.OrderResponse;
import ru.liga.enums.StatusCourier;

/**
 * Интерфейс описания api сервиса Courier.
 */
public interface CourierService {

    /**
     * Поиск курьеров по статусу.
     *
     * @param status статус курьера
     * @return список ответов курьеров
     */
    List<CourierResponse> findByStatus(StatusCourier status);

    /**
     * Поиск готовых заказов по статусу.
     *
     * @return список ответов курьеров
     */
    List<OrderResponse> findFinishOrder();

    /**
     * Изменение статуса курьера по его id.
     *
     * @param courierId идентификатор курьера
     * @param status    желаемый статус курьера
     */
    void changeStatusById(Long courierId, StatusCourier status);
}
