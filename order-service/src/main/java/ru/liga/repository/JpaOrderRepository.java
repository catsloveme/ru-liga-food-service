package ru.liga.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.liga.entity.Order;
import ru.liga.enums.StatusOrder;

import java.util.List;

public interface JpaOrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllOrders(Pageable pageable);
    List<Order> findOrdersByStatus(StatusOrder status,Pageable pageable);
    Order findOrderById(Long id,Pageable pageable);
    Order findOrderByCourierId(Long id,Pageable pageable);
    Order findOrderByRestaurantId(Long id,Pageable pageable);
    Order findOrderByCustomerId(Long id,Pageable pageable);
    @Query("update Order set Order.status= :status where Order.id = :id")
    void updateOrderStatus(@Param("id") Long orderId, @Param("status") StatusOrder status);
}
