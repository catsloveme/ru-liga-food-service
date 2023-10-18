package ru.liga.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum StatusRestaurant {
    ACCEPT("active"),
    COMPLETE("complete");

    private final String cmd;

    @Override
    public String toString() {
        return this.cmd;
    }
}
