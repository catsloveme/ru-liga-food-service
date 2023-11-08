package ru.liga.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liga.entity.Customer;

/**
 * Интерфейс для использования jpa методов с сущностью Customer.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    /**
     * Поиск всех заказчиков.
     *
     * @return список сущностей заказчиков
     */
    List<Customer> findAll();

    /**
     * Поиск заказчика по id.
     *
     * @param id идентификатор заказчика
     * @return сущность заказчика
     */
    Customer findCustomerById(UUID id);

}
