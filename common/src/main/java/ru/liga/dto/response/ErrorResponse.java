package ru.liga.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {
    private String description;
    private String code;
    private String exceptionName;
    private String exceptionMessage;

}
