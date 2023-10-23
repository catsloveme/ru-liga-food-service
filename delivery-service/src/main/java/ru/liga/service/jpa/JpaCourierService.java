package ru.liga.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.dto.response.CourierResponse;
import ru.liga.entity.Courier;
import ru.liga.enums.StatusCourier;
import ru.liga.mapper.JpaCourierMapper;
import ru.liga.repository.JpaCourierRepository;
import ru.liga.service.CourierService;

import java.util.List;

@Service
public class JpaCourierService implements CourierService {
    @Autowired
    JpaCourierRepository jpaCourierRepository;

    @Transactional(readOnly = true)
    public List<CourierResponse> findByStatus(StatusCourier status) {
        List<Courier> courier = jpaCourierRepository.findByStatus(status);
        return JpaCourierMapper.mapList(courier);
    }
    @Transactional
    public void changeOrderStatusById( Long courierId, StatusCourier status) {
        jpaCourierRepository.updateCourierStatus(courierId,status);
        jpaCourierRepository.flush();
    }

}
