package ru.liga.service.batis;

import java.util.List;
import lombok.RequiredArgsConstructor;
import ru.liga.api.OrderService;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.response.CreateOrderResponse;
import ru.liga.dto.response.OrderResponse;
import ru.liga.enums.StatusOrder;
import ru.liga.mapper.OrderMapperBatis;

/**
 * Сервис для работы с маппером MyBatis.
 */
@RequiredArgsConstructor
public class BatisOrderService implements OrderService {
    private final OrderMapperBatis orderMapper;

    /**
     * Поиск всех заказов.
     *
     * @return список ответов заказов
     */
    public List<OrderResponse> findAllOrders() {
        return orderMapper.findAllOrders();
    }

    /**
     * Поиск заказа по его id.
     *
     * @param orderId идентификатор заказа
     * @return ответ заказа
     */
    public OrderResponse findOrderById(Long orderId) {
        return orderMapper.findOrderById(orderId);
    }

    /**
     * Созадние заказа.
     *
     * @param createOrderRequest данные для запроса на создание заказа
     * @return ответ созадния заказа
     */
    public CreateOrderResponse addOrder(CreateOrderRequest createOrderRequest) {
        return orderMapper.addOrder(createOrderRequest);
    }

    /**
     * Обновление id курьера у заказа, найденного по id.
     *
     * @param orderId   идентификатор курьера
     * @param courierId идентификатор заказа
     */
    @Override
    public void updateCourierId(Long orderId, Long courierId) {

    }

    /**
     * Обновление статуса заказа.
     *
     * @param status  статус заказа
     * @param orderId идентификатор заказа
     */
    @Override
    public void updateOrderStatus(StatusOrder status, Long orderId) {

    }
}
