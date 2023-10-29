package ru.liga.service.jpa;

import lombok.RequiredArgsConstructor;
import ru.liga.api.CustomerService;
import ru.liga.dto.response.CustomerResponse;
import ru.liga.entity.Customer;
import ru.liga.mapping.abstraction.AbstractMapper;
import ru.liga.repository.CustomerRepository;

import java.util.List;

@RequiredArgsConstructor
public class JpaCustomerService implements CustomerService {
    private final CustomerRepository jpaCustomerRepository;
    private final AbstractMapper<Customer, CustomerResponse> mapper;

    public List<CustomerResponse> findAllCustomers() {
        List<Customer> customers = jpaCustomerRepository.findAll();
        return mapper.toDto(customers);
    }

    public CustomerResponse findCustomerById(Long id) {
        Customer customer = jpaCustomerRepository.findCustomerById(id);
        return mapper.toDto(customer);
    }

}
