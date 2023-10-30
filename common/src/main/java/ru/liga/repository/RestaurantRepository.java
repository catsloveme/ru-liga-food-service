package ru.liga.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.liga.entity.Restaurant;
import ru.liga.enums.StatusRestaurant;

/**
 * Интерфейс для использования jpa методов с сущностью Restaurant.
 */
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    /**
     * Поиск всех ресторанов.
     * @return список сущностей ресторанов
     */
    List<Restaurant> findAll();

    /**
     * Поиск ресторана по id.
     * @param id идентификатор ресторана
     * @return сущность ресторана
     */
    Optional<Restaurant> findById(Long id);

    /**
     * Обновление статуса ресторана, найденного по id.
     * @param status статус ресторана
     * @param id идентификатор ресторана
     */
    @Modifying
    @Query("update Restaurant res set res.status=:status where res.id =:id")
    void updateRestaurantStatus(StatusRestaurant status, Long id);
}
