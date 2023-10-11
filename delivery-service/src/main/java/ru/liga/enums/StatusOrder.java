package ru.liga.enums;

public enum StatusOrder {
    ACCEPT("active"),
    COMPLETE("complete");

    private final String cmd;

    StatusOrder(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return this.cmd;
    }
}
