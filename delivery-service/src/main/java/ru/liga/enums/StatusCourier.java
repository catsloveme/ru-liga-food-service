package ru.liga.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum StatusCourier {
    DELIVERY_PENDING("pending"),
    DELIVERY_PICKING("picking"),
    DELIVERY_DENIED("denied"),
    DELIVERY_DELIVERING("delivering"),
    DELIVERY_REFUNDED("refunded"),
    DELIVERY_COMPLETE("complete");

    private final String cmd;

    @Override
    public String toString() {
        return this.cmd;
    }
}
