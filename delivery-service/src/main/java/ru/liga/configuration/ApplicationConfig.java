package ru.liga.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.retry.annotation.EnableRetry;
import ru.liga.configuration.conditional.AccessType;

/**
 * Конфиругация приложения.
 */
@EnableRetry
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@Data
public class ApplicationConfig {
    private AccessType databaseAccessType;
    private final Long time = 600_000L;
    private final int maxAttempts = 2;



}
