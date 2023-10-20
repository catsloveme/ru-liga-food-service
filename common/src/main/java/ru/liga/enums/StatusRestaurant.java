package ru.liga.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum StatusRestaurant {
    KITCHEN_ACCEPTED("accepted"),
    KITCHEN_DENIED("denied"),
    KITCHEN_PREPARING("preparing"),
    KITCHEN_REFUNDED("refunded");
    private final String cmd;

    @Override
    public String toString() {
        return this.cmd;
    }
}
