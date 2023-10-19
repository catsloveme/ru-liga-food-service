package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.liga.entity.Restaurant;
import ru.liga.enums.StatusRestaurant;

import java.util.List;

public interface JpaRestaurantRepository extends JpaRepository<Restaurant,Long> {
    List<Restaurant> findAllRestaurants();
    Restaurant findRestaurantByAddress(String address);
    Restaurant findRestaurantById(Long id);
    @Query("update Restaurant set Restaurant.status= :status where Restaurant.id = :id")
    void updateRestaurantStatus(@Param("id") Long restaurantId, @Param("status") StatusRestaurant status);
}
