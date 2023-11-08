package ru.liga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Класс запускающий сервис Order.
 */
@EnableJpaRepositories
@SpringBootApplication
@SuppressWarnings("uncommentedmain")
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

