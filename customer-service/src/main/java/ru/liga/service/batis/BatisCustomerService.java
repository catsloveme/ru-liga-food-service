package ru.liga.service.batis;

import java.util.List;
import lombok.RequiredArgsConstructor;
import ru.liga.api.CustomerService;
import ru.liga.dto.response.CustomerResponse;
import ru.liga.mapper.CustomerMapper;

/**
 * Сервис для работы с маппером MyBatis.
 */
@RequiredArgsConstructor
public class BatisCustomerService implements CustomerService {

    private final CustomerMapper customerMapper;

    /**
     * Поиск всех заказчиков.
     * @return список ответов заказчиков
     */
    public List<CustomerResponse> findAllCustomers() {
        return customerMapper.findAllCustomers();
    }

    /**
     * Поиск заказчика по id.
     * @param id идентификатор заказчика
     * @return ответ заказчика
     */
    public CustomerResponse findCustomerById(Long id) {
        return customerMapper.findCustomerById(id);
    }


}
