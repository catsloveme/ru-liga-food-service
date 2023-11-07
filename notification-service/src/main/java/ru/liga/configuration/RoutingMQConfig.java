package ru.liga.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурационный класс для использования брокера сообщений.
 */
@Configuration
public class RoutingMQConfig {

    /**
     * Создание очереди сообщений для restaurant-service, получающей сообщение о создании нового заказа.
     * Создание очереди сообщений для courier-service, получающей сообщение о поиске курьера для готового заказа.
     * @return Declarables - Класс объединящий в себе очереди, тип обменника и байдинги(связи)
     */
    @Bean
    public Declarables myQueue() {
        Queue queueDirectRestaurant = new Queue("newOrderQueueToRestaurant", false);
        Queue queueDirectCouriers = new Queue("courierSearchQueueToCourier", false);
        Queue queueDirectToOrder = new Queue("toOrder", false);
        Queue queueDirectOrdersFromDelivery = new Queue("updateStatusOrderDelivery", false);
        DirectExchange directExchange = new DirectExchange("directExchange");

        return new Declarables(
            queueDirectRestaurant,
            queueDirectCouriers,
            queueDirectToOrder,
            queueDirectOrdersFromDelivery,
            directExchange,
            BindingBuilder.bind(queueDirectRestaurant).to(directExchange).with("new_order_to_restaurant"),
            BindingBuilder.bind(queueDirectCouriers).to(directExchange).with("courier_search_to_courier"),
            BindingBuilder.bind(queueDirectToOrder).to(directExchange)
                .with("message_to_order"),
            BindingBuilder.bind(queueDirectOrdersFromDelivery).to(directExchange)
                .with("update_status_order_delivery_picking")
        );
    }

    /**
     * Создание маппера для преобразования полученного сообщения.
     *
     * @return ObjectMapper
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

}

