package ru.liga.repository;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.liga.entity.RestaurantMenuItem;

@Repository
public interface RestaurantMenuItemRepository
    extends JpaRepository<RestaurantMenuItem, Long> {

    Optional<RestaurantMenuItem> findById(Long id);

    void deleteById(Long id);

    @Modifying
    @Query("update RestaurantMenuItem it set it.price= :price where it.id = :id")
    void updatePrice(@Param("price") BigDecimal price, @Param("id") Long id);

}
