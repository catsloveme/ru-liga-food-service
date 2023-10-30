package ru.liga.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.liga.entity.Courier;
import ru.liga.enums.StatusCourier;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {
    List<Courier> findAll();

    List<Courier> findByStatus(StatusCourier status);

    Optional<Courier> findById(Long id);

    @Modifying
    @Query("update Courier c set c.status= :status where c.id = :id")
    void updateCourierStatus(Long id, StatusCourier status);
}
