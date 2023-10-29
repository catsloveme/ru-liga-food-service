package ru.liga.configuration;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutingMQConfig {

    //Declarables - Класс объединящий в себе очереди, тип обменника и байдинги(связи)
    @Bean
    public Declarables myQueue() {
        Queue queueDirectRestaurant = new Queue("newOrderQueueToRestaurant", false);
        Queue queueDirectCouriers = new Queue("courierSearchQueueToCourier", false);
        DirectExchange directExchange = new DirectExchange("directExchange");

        return new Declarables(queueDirectRestaurant, queueDirectCouriers, directExchange,
            BindingBuilder.bind(queueDirectRestaurant).to(directExchange).with("new_order_to_restaurant"),
            BindingBuilder.bind(queueDirectCouriers).to(directExchange).with("courier_search_to_courier")
        );
    }

}

