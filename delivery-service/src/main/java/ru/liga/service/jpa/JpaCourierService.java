package ru.liga.service.jpa;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.api.CourierService;
import ru.liga.dto.response.CourierResponse;
import ru.liga.dto.response.OrderResponse;
import ru.liga.entity.Courier;
import ru.liga.entity.Order;
import ru.liga.enums.StatusCourier;
import ru.liga.enums.StatusOrder;
import ru.liga.mapping.CourierMapper;
import ru.liga.mapping.OrderMapper;
import ru.liga.repository.CourierRepository;
import ru.liga.repository.OrderRepository;

/**
 * Сервис для работы с репозиторием jpa.
 */
@Service
@RequiredArgsConstructor
public class JpaCourierService implements CourierService {

    private final CourierRepository jpaCourierRepository;
    private final OrderRepository orderRepository;
    private final CourierMapper courierMapper;
    private final OrderMapper orderMapper;

    /**
     * Поиск курьеров по статусу.
     *
     * @param status статус курьера
     * @return список ответов курьеров
     */
    public List<CourierResponse> findByStatus(StatusCourier status) {
        List<Courier> courier = jpaCourierRepository.findByStatus(status);
        return courierMapper.toDto(courier);
    }

    /**
     * Поиск готовых заказов по статусу.
     *
     * @return список ответов курьеров
     */
    public List<OrderResponse> findFinishOrder() {
        List<Order> orders = orderRepository.findOrderByStatus(StatusOrder.KITCHEN_FINISHED);
        return orderMapper.toDtos(orders);
    }

    /**
     * Изменение статуса курьера по его id.
     *
     * @param courierId идентификатор курьера
     * @param status    желаемый статус курьера
     */
    @Transactional
    public void changeStatusById(UUID courierId, StatusCourier status) {
        jpaCourierRepository.updateCourierStatus(courierId, status);
        jpaCourierRepository.flush();
    }

}
