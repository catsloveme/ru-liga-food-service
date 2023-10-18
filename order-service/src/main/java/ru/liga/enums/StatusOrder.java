package ru.liga.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum StatusOrder {
    CUSTOMER_CREATED("customer created"),
    CUSTOMER_PAID("customer paid"),
    CUSTOMER_CANCELLED("customer cancelled"),
    KITCHEN_ACCEPTED("kitchen accepted"),
    KITCHEN_DENIED("kitchen denied"),
    KITCHEN_PREPARING("kitchen preparing"),
    KITCHEN_REFUNDED("kitchen refunded"),
    DELIVERY_PENDING("delivery pending"),
    DELIVERY_PICKING("delivery picking"),
    DELIVERY_DENIED("delivery denied"),
    DELIVERY_DELIVERING("delivery delivering"),
    DELIVERY_REFUNDED("delivery refunded"),
    DELIVERY_COMPLETE("delivery complete");
    private final String cmd;

    @Override
    public String toString() {
        return this.cmd;
    }
}
