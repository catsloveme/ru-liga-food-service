package ru.liga.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ru.liga.api.OrderItemService;
import ru.liga.dto.request.CreateOrderItemRequest;
import ru.liga.dto.response.OrderItemResponse;

/**
 * Интерфейс (меппер) для работы с myBatis.
 */
@Mapper
public interface OrderItemMapperBatis extends OrderItemService {

    /**
     * Поиск всех частей заказа.
     *
     * @return список ответов частей всех заказов
     */
    List<OrderItemResponse> findAllOrderItems();

    /**
     * Поиск засти заказа по его id.
     *
     * @param id идентификатор части заказа
     * @return ответ части заказа
     */
    OrderItemResponse findOrderItemById(Long id);

    /**
     * Создание части заказа.
     *
     * @param creatingOrderItemRequest данные для запроса создания части заказа
     * @return ответ части заказа
     */
    OrderItemResponse addOrderItem(@Param("createOrderItemRequest") CreateOrderItemRequest creatingOrderItemRequest);
}
