package ru.liga.controller;

import clients.OrderFeign;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import ru.liga.entity.Courier;
import ru.liga.enums.StatusCourier;
import ru.liga.repository.JpaCourierRepository;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
public class QueueListener {
    private final JpaCourierRepository jpaCourierRepository;
    private final OrderFeign orderFeign;
    @RabbitListener(queues = "courier_search")
    public void searchCouriers(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Long idOrder = objectMapper.readValue(message, Long.class);
        List<Courier> activeCouriers = jpaCourierRepository.findByStatus(StatusCourier.ACTIVE);
        Random randomizer = new Random();
        Courier courierForDelivery = activeCouriers.get(randomizer.nextInt(activeCouriers.size()));
        orderFeign.updateCourierId(idOrder,courierForDelivery.getId());
    }
}
