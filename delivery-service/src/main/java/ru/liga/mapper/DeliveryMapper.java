package ru.liga.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
    @Select("SELECT courier_id as id, coordinates as address FROM couriers WHERE status = #{status}")
    List<CourierResponse> findByStatus(StatusCourier status);

    /**
     * Изменение статуса курьера по его id.
     * @param courierId идентификатор курьера
     * @param status    желаемый статус курьера
     */
    @Update("UPDATE couriers SET status = #{status} WHERE courier_id = #{courierId}")
    void changeOrderStatusById(@Param("courierId") Long courierId, @Param("status") StatusCourier status);
}
