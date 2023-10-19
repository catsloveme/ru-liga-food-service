package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.enums.StatusRestaurant;

import java.util.List;

public interface JpaRestaurantMenuItemRepository extends JpaRepository<RestaurantMenuItem,Long> {
    List<RestaurantMenuItem> findRestaurantMenuItemsByRestaurantId(Long restaurantId);
    RestaurantMenuItem findRestaurantMenuItemById(Long id);
    RestaurantMenuItem findRestaurantMenuItemByName(String name);
    @Query("update RestaurantMenuItem menu set menu.status= :status where menu.id = :id")
    void updateRestaurantMenuItemStatus(@Param("id") Long restaurantMenuItemId, @Param("status") StatusRestaurant status);
}
