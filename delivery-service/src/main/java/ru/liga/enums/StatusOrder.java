package ru.liga.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum StatusOrder {
    ACCEPT("active"),
    COMPLETE("complete");

    private final String cmd;
    @Override
    public String toString() {
        return this.cmd;
    }
}
