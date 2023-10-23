package ru.liga.service.batis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.batisMapper.DeliveryMapper;
import ru.liga.dto.response.CourierResponse;
import ru.liga.enums.StatusCourier;
import ru.liga.service.CourierService;

import java.util.List;

@Service
public class BatisCourierService implements CourierService {
    @Autowired
    DeliveryMapper deliveryMapper;

    @Transactional(readOnly = true)
    public List<CourierResponse> findByStatus(StatusCourier status) {
        return deliveryMapper.findByStatus(status);

    }
    @Transactional
    public void changeOrderStatusById( Long courierId, StatusCourier status) {
        deliveryMapper.changeOrderStatusById(courierId,status);
    }

}
