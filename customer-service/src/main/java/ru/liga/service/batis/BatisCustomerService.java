package ru.liga.service.batis;

import java.util.List;
import lombok.RequiredArgsConstructor;
import ru.liga.api.CustomerService;
import ru.liga.dto.response.CustomerResponse;
import ru.liga.mapper.CustomerMapper;

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
