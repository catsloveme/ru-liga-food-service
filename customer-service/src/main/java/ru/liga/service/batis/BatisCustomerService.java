package ru.liga.service.batis;

import lombok.RequiredArgsConstructor;
import ru.liga.api.CustomerService;
import ru.liga.dto.response.CustomerResponse;
import ru.liga.mapper.CustomerMapper;

import java.util.List;

@RequiredArgsConstructor
public class BatisCustomerService implements CustomerService {

    private final CustomerMapper customerMapper;

    public List<CustomerResponse> findAllCustomers() {
        return customerMapper.findAllCustomers();
    }

    public CustomerResponse findCustomerById(Long id) {
        return customerMapper.findCustomerById(id);
    }
}
