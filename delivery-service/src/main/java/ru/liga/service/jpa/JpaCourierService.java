package ru.liga.service.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.dto.response.CourierResponse;
import ru.liga.entity.Courier;
import ru.liga.enums.StatusCourier;
import ru.liga.mapping.CourierMapper;
import ru.liga.repository.CourierRepository;
import ru.liga.api.CourierService;

import java.util.List;

@RequiredArgsConstructor
public class JpaCourierService implements CourierService {

    private final CourierRepository jpaCourierRepository;
    private final CourierMapper mapper;

    public List<CourierResponse> findByStatus(StatusCourier status) {
        List<Courier> courier = jpaCourierRepository.findByStatus(status);
        return mapper.toDto(courier);
    }

    @Transactional
    public void changeOrderStatusById(Long courierId, StatusCourier status) {
        jpaCourierRepository.updateCourierStatus(courierId, status);
        jpaCourierRepository.flush();
    }

}
