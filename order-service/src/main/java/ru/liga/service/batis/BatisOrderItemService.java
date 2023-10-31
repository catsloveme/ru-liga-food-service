package ru.liga.service.batis;

import lombok.RequiredArgsConstructor;
import ru.liga.mapper.OrderItemMapperBatis;
import ru.liga.dto.request.CreateOrderItemRequest;
import ru.liga.dto.response.OrderItemResponse;
import ru.liga.api.OrderItemService;
import java.util.List;

/**
 * Сервис для работы с маппером MyBatis.
 */
@RequiredArgsConstructor
public class BatisOrderItemService implements OrderItemService {
    private final OrderItemMapperBatis orderItemMapper;

    /**
     * Поиск всех частей заказов.
     * @return  список ответов частей заказов
     */
    public List<OrderItemResponse> findAllOrderItems() {
        return orderItemMapper.findAllOrderItems();
    }

    /**
     * Поиск части заказа по его id.
     * @param id идентификатор части заказа
     * @return ответ части заказа
     */
    public OrderItemResponse findOrderItemById(Long id) {
        return orderItemMapper.findOrderItemById(id);
    }

    /**
     * Созадание части заказа
     * @param creatingOrderItemRequest данные для запроса создания части заказа
     * @return ответ части заказа
     */
    public OrderItemResponse addOrderItem(CreateOrderItemRequest creatingOrderItemRequest) {
        return orderItemMapper.addOrderItem(creatingOrderItemRequest);
    }

}

