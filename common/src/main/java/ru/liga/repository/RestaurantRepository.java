package ru.liga.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.liga.entity.Restaurant;
import ru.liga.enums.StatusRestaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findAll();

    Optional<Restaurant> findById(Long id);

    @Modifying
    @Query("update Restaurant res set res.status=:status where res.id =:id")
    void updateRestaurantStatus(StatusRestaurant status, Long id);
}
