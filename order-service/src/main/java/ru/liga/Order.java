package ru.liga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.liga.configuration.ApplicationConfig;

/**
 * Класс запускающий сервис Order.
 */
@EnableJpaRepositories
@EnableConfigurationProperties(ApplicationConfig.class)
@SpringBootApplication
public class Order {

    /**
     * Запуск сервиса Order.
     *
     * @param args массив строковых аргументов
     */
    public static void main(String[] args) {
        SpringApplication.run(Order.class, args);

    }
}

