package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liga.entity.Customer;
import java.util.List;
import java.util.Optional;
@Repository
public interface JpaCustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> findAll();
    Optional<Customer> findById(Long id);
    Customer findCustomerByPhone(String phone);
}
