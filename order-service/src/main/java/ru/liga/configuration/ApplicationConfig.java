package ru.liga.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import ru.liga.configuration.conditional.AccessType;

@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@Data
public class ApplicationConfig {
    private AccessType databaseAccessType;
}
