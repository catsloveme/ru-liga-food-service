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
     * Создание очереди сообщений для notification-service, получающей сообщение о создании нового заказа.
     *
     * @return Declarables - Класс объединящий в себе очереди, тип обменника и байдинги(связи)
     */
    @Bean
    public Declarables myQueue() {
        Queue queueCreateOrder = new Queue("newOrderQueueToNotification", false);
        DirectExchange directExchange = new DirectExchange("directExchange");

        return new Declarables(queueCreateOrder, directExchange,
            BindingBuilder.bind(queueCreateOrder).to(directExchange).with("new_order_to_notification")
        );
    }

}
