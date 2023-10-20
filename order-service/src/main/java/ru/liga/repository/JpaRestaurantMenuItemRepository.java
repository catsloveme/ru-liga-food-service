package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.entity.RestaurantMenuItem;
import java.util.List;
import java.util.Optional;

public interface JpaRestaurantMenuItemRepository extends JpaRepository<RestaurantMenuItem,Long> {
//    Boolean existByName(String name);
//    List<Long> findRestaurantIdByName(String name);
    List<RestaurantMenuItem> findByRestaurantId(Long restaurantId);
    Optional<RestaurantMenuItem> findById(Long id);
    List<RestaurantMenuItem> findByName(String name);

}
