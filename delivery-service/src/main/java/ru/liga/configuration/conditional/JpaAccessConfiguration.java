package ru.liga.configuration.conditional;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.liga.api.CourierService;
import ru.liga.dto.response.CourierResponse;
import ru.liga.entity.Courier;
import ru.liga.mapping.abstraction.AbstractMapper;
import ru.liga.repository.CourierRepository;
import ru.liga.service.jpa.JpaCourierService;

@RequiredArgsConstructor
@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class JpaAccessConfiguration {
    private final CourierRepository jpaCourierRepository;
    private final AbstractMapper<Courier, CourierResponse> mapper;
    @Bean("jpaCourierService")
    public CourierService courierService() {
        return new JpaCourierService(jpaCourierRepository, mapper);
    }


}
