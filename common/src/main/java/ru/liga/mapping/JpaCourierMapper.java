package ru.liga.mapping;

import ru.liga.dto.response.CourierResponse;
import ru.liga.entity.Courier;

import java.util.ArrayList;
import java.util.List;

public class JpaCourierMapper {
    public static CourierResponse map(Courier courier){
        String coordinates = courier.getCoordinates();
        return new CourierResponse(coordinates,null);
    }

    public static List<CourierResponse> mapList(List<Courier> couriers) {
        List<CourierResponse> result = new ArrayList<>();
        for (Courier courier : couriers) {
            result.add(map(courier));
        }
        return result;
    }
}
