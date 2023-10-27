package ru.liga.controller;

import ru.liga.clients.OrderFeign;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.liga.entity.Courier;
import ru.liga.enums.StatusCourier;
import ru.liga.repository.JpaCourierRepository;
import ru.liga.service.jpa.JpaCourierService;

import java.util.List;
import java.util.Random;

@Log4j2
@Service
@RequiredArgsConstructor
public class QueueListener {
    private final JpaCourierRepository jpaCourierRepository;
    private final JpaCourierService jpaCourierService;
    private final OrderFeign orderFeign;

    @RabbitListener(queues = "courierSearchQueue")
    public void searchCouriers(String message) throws JsonProcessingException {
        log.info("The message is received");
        ObjectMapper objectMapper = new ObjectMapper();
        Long idOrder = objectMapper.readValue(message, Long.class);
        List<Courier> activeCouriers = jpaCourierRepository.findByStatus(StatusCourier.DELIVERY_PENDING);
        Random randomizer = new Random();
        Courier courierForDelivery = activeCouriers.get(randomizer.nextInt(activeCouriers.size()));
        if (courierForDelivery != null) {
            Long courierIdForDelivery = courierForDelivery.getId();
            log.info("A courier id = {} has been selected for the order id = {}", courierIdForDelivery, idOrder);
            orderFeign.updateCourierId(courierIdForDelivery, idOrder);
            jpaCourierService.changeOrderStatusById(courierIdForDelivery, StatusCourier.DELIVERY_PICKING);
        }
    }
}
