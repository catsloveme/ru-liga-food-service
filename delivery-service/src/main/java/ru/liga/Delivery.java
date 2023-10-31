package ru.liga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.liga.clients.OrderFeign;
import ru.liga.configuration.ApplicationConfig;

/**
 * Класс запускающий сервис Delivery.
 */
@EnableFeignClients(clients = OrderFeign.class)
@EnableJpaRepositories
@EnableConfigurationProperties(ApplicationConfig.class)
@SpringBootApplication
@SuppressWarnings("uncommentedmain")
public class Delivery {
    /**
     * Запуск сервиса Delivery
     * @param args массив строковых аргументов
     */
    public static void main(String[] args) {
        SpringApplication.run(Delivery.class, args);
    }
}
