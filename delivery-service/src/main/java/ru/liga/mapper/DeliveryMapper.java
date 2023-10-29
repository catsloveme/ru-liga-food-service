package ru.liga.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import ru.liga.dto.response.CourierResponse;
import ru.liga.enums.StatusCourier;
import ru.liga.api.CourierService;
import java.util.List;

@Mapper
public interface DeliveryMapper extends CourierService {
    @Select("SELECT courier_id as address, coordinates as distance fROM couriers")
    List<CourierResponse> findByStatus(StatusCourier status);

    @Select("SELECT courier_id as address, coordinates as distance FROM couriers WHERE courier_id=#{id};")
    CourierResponse findById(@Param("id") Long id);

    void changeOrderStatusById(@Param("courierId") Long courierId, @Param("status") StatusCourier status);
}
