package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.liga.entity.Restaurant;
import ru.liga.enums.StatusRestaurant;

import java.util.List;

public interface JpaRestaurantRepository extends JpaRepository<Restaurant,Long> {
    List<Restaurant> findAll();
    Restaurant findByAddress(String address);
    Restaurant findRestaurantById(Long id);
    @Modifying
    @Query("update Restaurant res set res.status= :status where res.id = :id")
    void updateRestaurantStatus( @Param("status") StatusRestaurant status, @Param("id") Long id);
}
