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
        Queue queueDirectCouriers = new Queue("newOrderQueueToNotification", false);
        DirectExchange directExchange = new DirectExchange("directExchange");

        return new Declarables(queueDirectCouriers, directExchange,
                BindingBuilder.bind(queueDirectCouriers).to(directExchange).with("new_order"));
    }

}
