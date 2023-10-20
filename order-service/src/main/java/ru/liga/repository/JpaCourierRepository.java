package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.liga.entity.Courier;
import ru.liga.enums.StatusCourier;
import java.util.List;
import java.util.Optional;

public interface JpaCourierRepository extends JpaRepository<Courier,Long> {
    List<Courier> findAll();
    List<Courier> findByStatus(StatusCourier status);
    Optional<Courier> findById(Long id);
    @Query("update Courier c set c.status= :status where c.id = :id")
    void updateCourierStatus(@Param("id") Long courierId, @Param("status") StatusCourier status);
}
