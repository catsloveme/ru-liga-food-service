package ru.liga.api;

import ru.liga.enums.StatusOrder;

public interface RabbitMQProducerService {

    void sendStatus(StatusOrder status, String routingKey);

}
