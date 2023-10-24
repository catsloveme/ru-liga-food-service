package ru.liga.configuration.conditional;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.liga.api.CourierService;
import ru.liga.repository.JpaCourierRepository;
import ru.liga.service.jpa.JpaCourierService;

@RequiredArgsConstructor
@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class JpaAccessConfiguration {
    private final JpaCourierRepository jpaCourierRepository;
    @Primary
    @Bean("jpaCourierService")
    public CourierService courierService() {
        return new JpaCourierService(jpaCourierRepository);
    }


}
