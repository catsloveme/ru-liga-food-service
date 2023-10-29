package ru.liga.service.batis;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.mapper.OrderItemMapperBatis;
import ru.liga.dto.request.CreateOrderItemRequest;
import ru.liga.dto.response.OrderItemResponse;
import ru.liga.api.OrderItemService;

import java.util.List;

@RequiredArgsConstructor
public class BatisOrderItemService implements OrderItemService {
    private final OrderItemMapperBatis orderItemMapper;

    // Pageable firstPageWithTenElements = PageRequest.of(0, 10);

    @Transactional(readOnly = true)
    public List<OrderItemResponse> findAllOrderItems() {
        return orderItemMapper.findAllOrderItems();
    }

    @Transactional(readOnly = true)
    public OrderItemResponse findOrderItemById(Long id) {
        return orderItemMapper.findOrderItemById(id);
    }

    @Transactional
    public OrderItemResponse addOrderItem(CreateOrderItemRequest creatingOrderItemRequest) {
        return orderItemMapper.addOrderItem(creatingOrderItemRequest);
    }
//    @Transactional
//    public void deleteOrderItemById(Long id){
//        jpaOrderItemRepository.deleteById(id);
//    }
}

