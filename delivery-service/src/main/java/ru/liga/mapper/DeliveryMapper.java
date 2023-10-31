package ru.liga.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import ru.liga.api.CourierService;
import ru.liga.dto.response.CourierResponse;
import ru.liga.enums.StatusCourier;

/**
 * Интерфейс (меппер) для работы с myBatis.
 */
@Mapper
public interface DeliveryMapper extends CourierService {
    /**
     * Поиск курьеров по статусу.
     * @param status статус курьера
     * @return список ответов курьеров
     */
    @Select("SELECT courier_id as address, coordinates as distance fROM couriers")
    List<CourierResponse> findByStatus(StatusCourier status);

    /**
     * Поиск курьера по id.
     * @param id идентификатор курьера
     * @return ответ курьера
     */
    @Select("SELECT courier_id as address, coordinates as distance FROM couriers WHERE courier_id=#{id};")
    CourierResponse findById(@Param("id") Long id);

    /**
     * Изменение статуса курьера по его id.
     * @param courierId идентификатор курьера
     * @param status    желаемый статус курьера
     */
    void changeOrderStatusById(@Param("courierId") Long courierId, @Param("status") StatusCourier status);
}
