package ru.liga.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.liga.entity.Order;
import ru.liga.enums.StatusOrder;

/**
 * Интерфейс для использования jpa методов с сущностью Order.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    /**
     * Поиск всех заказов.
     * @return список всех сущностей заказов
     */
    List<Order> findAll(); //Pageable pageable);

    /**
     * Поиск заказа по id.
     * @param id идентификатор заказа
     * @return сущность заказа
     */
    Order findOrderById(Long id); //, Pageable pageable);

    /**
     * Обновление статуса заказа по id.
     * @param status статус заказа
     * @param id идентификатор заказа
     */
    @Modifying
    @Query("update Order ord set ord.status= :status where ord.id = :id")
    void updateOrderStatus(StatusOrder status, Long id);

    /**
     * Обновление id курьера в заказе, пойденом по id заказа.
     * @param courierId идентификатор курьера
     * @param orderId идентификатор заказа
     */
    @Modifying
    @Query("update Order ord set ord.courier.id= :courierId where ord.id = :orderId")
    void updateCourierId(Long courierId, Long orderId);
}
