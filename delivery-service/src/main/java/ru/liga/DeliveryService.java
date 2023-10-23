package ru.liga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.liga.configuration.ApplicationConfig;

@EnableJpaRepositories
@EnableConfigurationProperties(ApplicationConfig.class)
@SpringBootApplication
public class DeliveryService {
    public static void main(String[] args) {
        SpringApplication.run(DeliveryService.class, args);
    }
}