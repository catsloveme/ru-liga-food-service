package ru.liga.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.liga.entity.Courier;
import ru.liga.enums.StatusCourier;

/**
 * Интерфейс для использования jpa методов с сущностью Courier.
 */
@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {
    /**
     * Поиск всех курьеров.
     * @return список сущностей Courier
     */
    List<Courier> findAll();

    /**
     * Поиск курьеров по статусу.
     * @param status статус курьера
     * @return список сущностей Courier
     */
    List<Courier> findByStatus(StatusCourier status);

    /**
     * Поиск курьера по id.
     * @param id идентификатор курьера
     * @return сущность курьер типа Optional
     */
    Optional<Courier> findById(Long id);

    /**
     * Обновление статуса курьера по id курьера.
     * @param id идентификатор курьера
     * @param status желаемый статус курьера
     */
    @Modifying
    @Query("update Courier c set c.status= :status where c.id = :id")
    void updateCourierStatus(Long id, StatusCourier status);
}
