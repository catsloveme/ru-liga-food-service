package ru.liga.service.batisMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ru.liga.dto.response.CourierResponse;
import ru.liga.enums.StatusCourier;
import ru.liga.service.CourierService;
import java.util.List;

@Mapper
public interface DeliveryMapper extends CourierService {

    List<CourierResponse> findByStatus(StatusCourier status) ;

    void changeOrderStatusById( @Param("courierId") Long courierId, @Param("status") StatusCourier status);
}
