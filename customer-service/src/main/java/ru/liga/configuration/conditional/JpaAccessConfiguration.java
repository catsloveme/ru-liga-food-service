package ru.liga.configuration.conditional;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.liga.mapping.CustomerMapper;
import ru.liga.repository.*;
import ru.liga.service.jpa.JpaCustomerService;


@RequiredArgsConstructor
@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class JpaAccessConfiguration {

    private final JpaCustomerRepository jpaCustomerRepository;
    private final CustomerMapper customerMapperMapStruct;

    @Primary
    @Bean("jpaCustomerService")
    public JpaCustomerService orderService() {
        return new JpaCustomerService(jpaCustomerRepository, customerMapperMapStruct);
    }

}
