package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.liga.entity.OrderItem;
import java.math.BigDecimal;
import java.util.List;

public interface JpaOrderItemRepository extends JpaRepository<OrderItem,Long> {
    List<OrderItem> findAll();
    List<OrderItem> findByOrderId(Long id);
    OrderItem findOrderItemById(Long id);
    @Modifying
    @Query("update OrderItem ord set ord.price= :price where ord.id = :id")
    void updateOrderItemPrice(BigDecimal price, Long id);

}
