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
        Queue queueDirectCreateOrder = new Queue("createOrderQueue", false);
        Queue queueDirectPaidOrder = new Queue("paidOrderQueue", false);
        Queue queueDirectCancelOrder = new Queue("cancelOrderQueue", false);
        DirectExchange directExchange = new DirectExchange("directExchange");

        return new Declarables(queueDirectCreateOrder,queueDirectPaidOrder,queueDirectCancelOrder, directExchange,
                BindingBuilder.bind(queueDirectCreateOrder).to(directExchange).with("create_order"),
                BindingBuilder.bind(queueDirectPaidOrder).to(directExchange).with("paid_order"),
                BindingBuilder.bind(queueDirectCancelOrder).to(directExchange).with("cancel_order"));
    }

}
