package ru.liga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Класс запускающий сервис Restaurant.
 */
@EnableFeignClients
@EnableJpaRepositories
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
