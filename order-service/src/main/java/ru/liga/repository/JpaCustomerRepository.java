package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.entity.Customer;
import java.util.List;

public interface JpaCustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> findAllCustomers();
    Customer findCustomerById(Long id);
    Customer findCustomerByPhone(String phone);
}
