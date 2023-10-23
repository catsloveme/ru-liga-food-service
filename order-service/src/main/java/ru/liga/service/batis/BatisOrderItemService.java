package ru.liga.service.batis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.batisMapper.orderItem.OrderItemMapper;
import ru.liga.dto.request.CreateOrderItemRequest;
import ru.liga.dto.response.OrderItemResponse;
import ru.liga.service.OrderItemService;

import java.util.List;


public class BatisOrderItemService implements OrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;
    Pageable firstPageWithTenElements = PageRequest.of(0, 10);

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

