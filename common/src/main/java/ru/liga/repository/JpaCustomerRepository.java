package ru.liga.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.liga.entity.Customer;
import ru.liga.entity.Order;
import ru.liga.enums.StatusOrder;

import java.util.List;

@Repository
public interface JpaCustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> findAll();//Pageable pageable);

    Customer findCustomerById(Long id);//, Pageable pageable);

}
