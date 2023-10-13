package ru.liga.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse {
    private List<DeliveryResponse> orderResponse;
    private Integer pageIndex;
    private Integer pageCount;
}
