package ru.liga.service.rabbitMQ;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.enums.StatusRestaurant;

@Service
@RequiredArgsConstructor
public class CourierService {
    private final RabbitMQProducerServiceImpl rabbitMQProducerService;

    private final ObjectMapper objectMapper;

    public void sendStatusAccept(StatusRestaurant statusAccept) {
        if (statusAccept.equals(StatusRestaurant.KITCHEN_ACCEPTED)) {
            rabbitMQProducerService.sendMessage(statusAccept.toString(), "courier.search");
        }
    }

}
