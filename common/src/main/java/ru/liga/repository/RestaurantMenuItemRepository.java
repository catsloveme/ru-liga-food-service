package ru.liga.repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.liga.entity.RestaurantMenuItem;

/**
 * Интерфейс для использования jpa методов с сущностью RestaurantMenuItem.
 */
@Repository
public interface RestaurantMenuItemRepository
    extends JpaRepository<RestaurantMenuItem, UUID> {

    /**
     * Поиск ресторанного блюда по идентификатору.
     *
     * @param id идентификатор меню
     * @return сущность ресторанного меню
     */
    Optional<RestaurantMenuItem> findById(UUID id);

    /**
     * Удаление ресторанного блюда по id меню.
     *
     * @param id идентификатор меню
     */
    void deleteById(UUID id);

    /**
     * Обновление цены ресторанного блюда.
     *
     * @param price цена
     * @param id    идентификатор блюда
     */
    @Modifying
    @Query("update RestaurantMenuItem it set it.price= :price where it.id = :id")
    void updatePrice(@Param("price") BigDecimal price, @Param("id") UUID id);

}
