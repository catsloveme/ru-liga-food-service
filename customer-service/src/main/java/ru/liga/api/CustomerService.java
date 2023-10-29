package ru.liga.api;

import ru.liga.dto.response.CustomerResponse;
import java.util.List;

public interface CustomerService {
    List<CustomerResponse> findAllCustomers();

    CustomerResponse findCustomerById(Long id);

}
