package ru.liga.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.liga.entity.Order;
import ru.liga.enums.StatusOrder;

import java.util.List;
@Repository
public interface JpaOrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAll();//Pageable pageable);
    List<Order> findByStatus(StatusOrder status,Pageable pageable);
    Order findOrderById(Long id);//, Pageable pageable);
    Order findByCourierId(Long id);//,Pageable pageable);
    Order findByRestaurantId(Long id);//,Pageable pageable);
    Order findByCustomerId(Long id);//,Pageable pageable);
    @Modifying
    @Query("update Order ord set ord.status= :status where ord.id = :id")
    void updateOrderStatus( StatusOrder status, Long id);

    @Modifying
    @Query("update Order ord set ord.courier.id= :courierId where ord.id = :orderId")
    void updateCourierId(Long courierId, Long orderId);
}
