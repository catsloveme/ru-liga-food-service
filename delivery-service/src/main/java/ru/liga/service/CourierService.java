package ru.liga.service;

import ru.liga.dto.response.CourierResponse;
import ru.liga.enums.StatusCourier;
import java.util.List;

public interface CourierService {
    List<CourierResponse> findByStatus(StatusCourier status) ;

    void changeOrderStatusById( Long courierId, StatusCourier status);
}
