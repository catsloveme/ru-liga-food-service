package ru.liga.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.liga.entity.OrderItem;

/**
 * Интерфейс для использования jpa методов с сущностью OrderItem.
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    /**
     * Поиск всех частей заказов по всем заказам.
     * @return список всех сущностей частей заказов
     */
    List<OrderItem> findAll();

    /**
     * Поиск всех частей заказов по id заказа.
     *
     * @param id идентификатор заказа
     * @return список всех сущностей частей заказов
     */
    List<OrderItem> findByOrderId(UUID id);

    /**
     * Поиск всех частей заказа по id части заказа.
     *
     * @param id идентификатор заказа
     * @return сущность части заказа
     */
    Optional<OrderItem> findById(UUID id);

    /**
     * удаление части заказа по id.
     *
     * @param id идентификатор части заказа
     */
    void deleteById(UUID id);

    /**
     * Обновление цены части заказа.
     *
     * @param price цена
     * @param id    идентификатор части заказа
     */
    @Modifying
    @Query("update OrderItem ord set ord.price= :price where ord.id = :id")
    void updateOrderItemPrice(BigDecimal price, UUID id);

}
