package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.liga.entity.Courier;
import ru.liga.enums.StatusCourier;
import java.util.List;

public interface JpaCourierRepository extends JpaRepository<Courier,Long> {
    List<Courier> findAllCouriers();
    List<Courier> findCouriersByStatus(StatusCourier status);
    Courier findCourierById(Long id);
    @Query("update Courier set Courier.status= :status where Courier.id = :id")
    void updateCourierStatus(@Param("id") Long courierId, @Param("status") StatusCourier status);
}
