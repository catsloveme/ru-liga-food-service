package ru.liga.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum StatusOrder {
    ACTIVE("active"),
    COMPLETE("complete"),
    DENIED("denied");
    private final String cmd;

    @Override
    public String toString() {
        return this.cmd;
    }
}
