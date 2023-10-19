package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.liga.entity.OrderItem;
import java.math.BigDecimal;
import java.util.List;

public interface JpaOrderItemRepository extends JpaRepository<OrderItem,Long> {
    List<OrderItem> findAllOrderItems();
    List<OrderItem> findOrderItemsByOrderId(Long id);
    OrderItem findOrderItemById(Long id);
    @Query("update OrderItem set OrderItem.price= :price where OrderItem.id = :id")
    void updateOrderItemPrice(@Param("id") Long OrderItemId, @Param("price") BigDecimal price);

}
