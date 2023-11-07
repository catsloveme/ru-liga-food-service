package ru.liga.configuration;

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
     *
     * @return Declarables - Класс объединящий в себе очереди, тип обменника и байдинги(связи)
     */
    @Bean
    public Declarables myQueue() {
        Queue queueDirectNotification = new Queue("updateStatus", false);
        DirectExchange directExchange = new DirectExchange("directExchange");

        return new Declarables(queueDirectNotification, directExchange,
            BindingBuilder.bind(queueDirectNotification).to(directExchange).with("courier_is_found")

        );
    }

}

