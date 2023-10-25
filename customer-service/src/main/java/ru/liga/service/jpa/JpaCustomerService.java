package ru.liga.service.jpa;

import lombok.RequiredArgsConstructor;
import ru.liga.api.CustomerService;
import ru.liga.dto.response.CustomerResponse;
import ru.liga.entity.Customer;
import ru.liga.enums.StatusOrder;
import ru.liga.mapping.CustomerMapperMapStruct;
import ru.liga.repository.JpaCustomerRepository;

import java.util.List;
@RequiredArgsConstructor
public class JpaCustomerService implements CustomerService {
    private final JpaCustomerRepository jpaCustomerRepository;

    public List<CustomerResponse> findAllCustomers() {
        List<Customer> customers = jpaCustomerRepository.findAll();
        return CustomerMapperMapStruct.INSTANCE.customersToCustomersResponse(customers);
    }

    public CustomerResponse findCustomerById(Long id) {
        Customer customer = jpaCustomerRepository.findCustomerById(id);
        return CustomerMapperMapStruct.INSTANCE.customerToCustomerResponse(customer);
    }

}
