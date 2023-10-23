package ru.liga.service.batisMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ru.liga.dto.request.RestaurantMenuItemRequest;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.service.RestaurantMenuItemService;
import java.math.BigDecimal;


@Mapper
public interface RestaurantMenuItemMapper extends RestaurantMenuItemService {

    void updatePrice(@Param("price") BigDecimal price, @Param("id")Long id);
    RestaurantMenuItemResponse addRestaurantMenuItem(@Param("request")RestaurantMenuItemRequest request) ;
    void deleteRestaurantMenuItemById(Long id);

}
