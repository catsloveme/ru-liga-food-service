package ru.liga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.liga.configuration.ApplicationConfig;

/**
 * Класс запускающий сервис Restaurant.
 */
@EnableFeignClients
@EnableJpaRepositories
@EnableConfigurationProperties(ApplicationConfig.class)
@SpringBootApplication
@SuppressWarnings("uncommentedmain")
public class Restaurant {

    /**
     * Запуск сервиса Notification.
     *
     * @param args массив строковых аргументов
     */
    public static void main(String[] args) {
        SpringApplication.run(Restaurant.class, args);
    }
}
