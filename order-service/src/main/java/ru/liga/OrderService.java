package ru.liga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EnableConfigurationProperties
@SpringBootApplication
public class OrderService {

    public static void main(String[] args) {
        SpringApplication.run(OrderService.class, args);
    }
}

