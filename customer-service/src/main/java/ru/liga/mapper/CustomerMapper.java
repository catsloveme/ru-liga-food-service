package ru.liga.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ru.liga.api.CustomerService;
import ru.liga.dto.response.CustomerResponse;

/**
 * Интерфейс (меппер) для работы с myBatis.
 */
@Mapper
public interface CustomerMapper extends CustomerService {
    /**
     * Поиск всех заказчиков.
     * @return список ответов заказчиков.
     */
    @Select("SELECT customer_id, phone, email, address FROM customers;")
    List<CustomerResponse> findAllCustomers();

    /**
     * Поиск заказчика по id.
     * @param id идентификатор заказчика
     * @return ответ заказчика
     */
    @Select("SELECT customer_id, phone, email, address FROM customers WHERE customer_id=#{id};")
    CustomerResponse findCustomerById(Long id);
}
