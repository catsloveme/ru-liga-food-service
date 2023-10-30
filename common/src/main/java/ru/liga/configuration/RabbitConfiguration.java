package ru.liga.configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//EnableRabbit - обязательная анотация, без нее рэбит не будет работать
@EnableRabbit
@Configuration
public class RabbitConfiguration {
    private static final String USER_NAME_AND_PASSWORD = "guest";
    private static final String LOCALHOST = "localhost";

    /**
     * Бин создания соединения с сервером рэбит.
     *
     * @return ConnectionFactory
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory =
            new CachingConnectionFactory(LOCALHOST);
        cachingConnectionFactory.setUsername(USER_NAME_AND_PASSWORD);
        cachingConnectionFactory.setPassword(USER_NAME_AND_PASSWORD);
        return cachingConnectionFactory;
    }

    /**
     * AmqpAdmin занимается обслуживанием очередей, обменника, сообщений.
     * @return AmqpAdmin
     */
    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    /**
     * RabbitTemplate имеет гибкие настройки, такие как
     * явное указание типа конвертации.
     * @return RabbitTemplate основной класс для отправки сообщения
     */
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return new RabbitTemplate(connectionFactory());
    }
}
