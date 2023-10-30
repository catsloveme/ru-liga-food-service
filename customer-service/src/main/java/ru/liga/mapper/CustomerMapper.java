package ru.liga.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ru.liga.api.CustomerService;
import ru.liga.dto.response.CustomerResponse;

@Mapper

public interface CustomerMapper extends CustomerService {
    @Select("SELECT customer_id, phone, email, address FROM customers;")
    List<CustomerResponse> findAllCustomers();

    @Select("SELECT customer_id, phone, email, address FROM customers WHERE customer_id=#{id};")
    CustomerResponse findCustomerById(Long id);
}
