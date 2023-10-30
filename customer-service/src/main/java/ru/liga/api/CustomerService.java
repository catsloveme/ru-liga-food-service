package ru.liga.api;

import java.util.List;
import ru.liga.dto.response.CustomerResponse;

/**
 * Интерфейс описания api сервиса Customer.
 */
public interface CustomerService {
    /**
     * Поиск всех заказчиков.
     * @return список ответов заказчиков
     */
    List<CustomerResponse> findAllCustomers();

    /**
     * Найти заказчика по id.
     * @param id идентификатор заказчика
     * @return ответ заказчика
     */
    CustomerResponse findCustomerById(Long id);

}
