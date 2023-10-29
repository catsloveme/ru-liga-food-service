package ru.liga.service.batis;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.mapper.DeliveryMapper;
import ru.liga.dto.response.CourierResponse;
import ru.liga.enums.StatusCourier;
import ru.liga.api.CourierService;

import java.util.List;

@RequiredArgsConstructor
public class BatisCourierService implements CourierService {

    private final DeliveryMapper deliveryMapper;

    @Transactional(readOnly = true)
    public List<CourierResponse> findByStatus(StatusCourier status) {
        return deliveryMapper.findByStatus(status);

    }

    @Transactional
    public void changeOrderStatusById(Long courierId, StatusCourier status) {
        deliveryMapper.changeOrderStatusById(courierId, status);
    }

}
