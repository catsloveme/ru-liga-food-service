package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.liga.entity.OrderItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface JpaOrderItemRepository extends JpaRepository<OrderItem,Long> {
    List<OrderItem> findAll();
    List<OrderItem> findByOrderId(Long id);
    Optional<OrderItem> findById(Long id);

    void deleteById(Long id);

    @Modifying
    @Query("update OrderItem ord set ord.price= :price where ord.id = :id")
    void updateOrderItemPrice(BigDecimal price, Long id);

}
