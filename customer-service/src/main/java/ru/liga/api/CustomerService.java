package ru.liga.api;

import java.util.List;
import ru.liga.dto.response.CustomerResponse;

public interface CustomerService {
    List<CustomerResponse> findAllCustomers();

    CustomerResponse findCustomerById(Long id);

}
