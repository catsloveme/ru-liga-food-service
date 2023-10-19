package ru.liga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.liga.dto.response.OrderResponse;
import ru.liga.mapper.JpaOrderMapper;
import ru.liga.repository.JpaOrderRepository;

import java.util.List;

public class JpaOrderService {
    @Autowired
    private JpaOrderRepository jpaOrderRepository;
    Pageable firstPageWithTenElements = PageRequest.of(0, 10);
    public List<OrderResponse> findAllOrders(){
        return JpaOrderMapper.mapList(jpaOrderRepository.findAllOrders(firstPageWithTenElements));
    }
    public OrderResponse findOrderById( Long id){
        return JpaOrderMapper.map(jpaOrderRepository.findOrderById(id, firstPageWithTenElements));
    }

//    public CreateOrderResponse createNewOrder( RequestCreatingOrder requestCreatingOrder){
//        return
//    }
}
