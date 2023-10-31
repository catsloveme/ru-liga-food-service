package ru.liga.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import ru.liga.configuration.conditional.AccessType;

/**
 * Конфиругация приложения.
 */
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@Data
public class ApplicationConfig {
    private AccessType databaseAccessType;
    private final Long time = 600_000L;
    private final int maxAttempts = 2;

    /**
     * Метод для исподльзования Retry, который дает возможность автоматического повторного запуска неудачной операции.
     * @return RetryTemplate
     */
    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(time);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(maxAttempts);
        retryTemplate.setRetryPolicy(retryPolicy);

        return retryTemplate;
    }

    /**
     * Создание маппера для преобразования полученного сообщения.
     * @return ObjectMapper
     */
    @Bean
    public ObjectMapper createObjectMapper() {
        return new ObjectMapper();
    }
}
