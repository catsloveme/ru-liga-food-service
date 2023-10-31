package ru.liga.api;

import ru.liga.dto.response.CourierResponse;
import ru.liga.enums.StatusCourier;
import java.util.List;

/**
 * Интерфейс описания api сервиса Courier.
 */
public interface CourierService {

    /**
     * Поиск курьеров по статусу.
     * @param status статус курьера
     * @return списов ответов курьеров
     */
    List<CourierResponse> findByStatus(StatusCourier status);

    /**
     * Изменение статуса курьера по его id.
     * @param courierId идентификатор курьера
     * @param status желаемый статус курьера
     */
    void changeOrderStatusById(Long courierId, StatusCourier status);
}
