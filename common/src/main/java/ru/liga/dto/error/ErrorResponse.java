package ru.liga.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private String description;
    private String code;
    private String exceptionName;
    private String exceptionMessage;
}
