package ru.liga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Класс запускающий сервис Notification.
 */
@SpringBootApplication
public class Notification {
    /**
     * Запуск сервиса Notification.
     * @param args массив строковых аргументов
     */
    public static void main(String[] args) {
        SpringApplication.run(Notification.class, args);
    }
}
