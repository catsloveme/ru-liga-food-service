package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.liga.entity.Order;
import ru.liga.enums.StatusOrder;

import java.util.List;

public interface JpaOrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllOrders();
    List<Order> findOrdersByStatus(StatusOrder status);
    Order findOrderById(Long id);
    Order findOrderByCourierId(Long id);
    Order findOrderByRestaurantId(Long id);
    Order findOrderByCustomerId(Long id);
    @Query("update Order set Order.status= :status where Order.id = :id")
    void updateOrderStatus(@Param("id") Long orderId, @Param("status") StatusOrder status);
}
