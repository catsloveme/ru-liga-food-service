package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.liga.entity.RestaurantMenuItem;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Repository
public interface JpaRestaurantMenuItemRepository extends JpaRepository<RestaurantMenuItem,Long> {

    List<RestaurantMenuItem> findByRestaurantId(Long restaurantId);
    Optional<RestaurantMenuItem> findById(Long id);
    List<RestaurantMenuItem> findByName(String name);
    void deleteById(Long id);
    @Modifying
    @Query("update RestaurantMenuItem it set it.price= :price where it.id = :id")
    void updatePrice(@Param("price") BigDecimal price, @Param("id") Long id);


}
