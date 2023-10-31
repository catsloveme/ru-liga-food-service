package ru.liga.service.batis;

import lombok.RequiredArgsConstructor;
import ru.liga.mapper.DeliveryMapper;
import ru.liga.dto.response.CourierResponse;
import ru.liga.enums.StatusCourier;
import ru.liga.api.CourierService;
import java.util.List;

/**
 * Сервис для работы с маппером MyBatis.
 */
@RequiredArgsConstructor
public class BatisCourierService implements CourierService {

    private final DeliveryMapper deliveryMapper;

    /**
     * Поиск курьеров по статусу.
     * @param status статус курьера
     * @return список ответов курьеров
     */
    public List<CourierResponse> findByStatus(StatusCourier status) {
        return deliveryMapper.findByStatus(status);

    }

    /**
     * Изменение статуса курьера по его id.
     * @param courierId идентификатор курьера
     * @param status    желаемый статус курьера
     */
    public void changeOrderStatusById(Long courierId, StatusCourier status) {
        deliveryMapper.changeOrderStatusById(courierId, status);
    }

}
