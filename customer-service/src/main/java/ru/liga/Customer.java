/**
 * Info about this package doing something for package-info.java file.
 */

package ru.liga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.liga.configuration.ApplicationConfig;
@SuppressWarnings("uncommentedmain")
@EnableJpaRepositories
@EnableConfigurationProperties(ApplicationConfig.class)
@SpringBootApplication
public class Customer {
    /**
     * Запуск сервиса customer
     * @param args массив строковых аргументов
     */
    public static void main(String[] args) {
        SpringApplication.run(Customer.class, args);
    }
}
