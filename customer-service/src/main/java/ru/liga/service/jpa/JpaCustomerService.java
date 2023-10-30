package ru.liga.service.jpa;

import java.util.List;
import lombok.RequiredArgsConstructor;
import ru.liga.api.CustomerService;
import ru.liga.dto.response.CustomerResponse;
import ru.liga.entity.Customer;
import ru.liga.mapping.abstraction.AbstractMapper;
import ru.liga.repository.CustomerRepository;

/**
 * Сервис для работы с репозиторием jpa.
 */
@RequiredArgsConstructor
public class JpaCustomerService implements CustomerService {
    private final CustomerRepository jpaCustomerRepository;
    private final AbstractMapper<Customer, CustomerResponse> mapper;

    /**
     * Поиск всех заказчиков
     * @return список ответов заказчиков
     */
    public List<CustomerResponse> findAllCustomers() {
        List<Customer> customers = jpaCustomerRepository.findAll();
        return mapper.toDto(customers);
    }

    /**
     * Поиск заказчика по id.
     * @param id идентификатор заказчика
     * @return ответ заказчика
     */
    public CustomerResponse findCustomerById(Long id) {
        Customer customer = jpaCustomerRepository.findCustomerById(id);
        return mapper.toDto(customer);
    }

}
