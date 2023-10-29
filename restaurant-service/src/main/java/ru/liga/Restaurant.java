package ru.liga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.liga.configuration.ApplicationConfig;

@EnableFeignClients
@EnableJpaRepositories
@EnableConfigurationProperties(ApplicationConfig.class)
@SpringBootApplication
public class Restaurant {
    public static void main(String[] args) {
        SpringApplication.run(Restaurant.class, args);
    }
}
