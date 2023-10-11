package ru.liga.enums;

public enum StatusOrder {
    ACTIVE("active"),
    COMPLETE("complete"),
    DENIED("denied");
    private final String cmd;

    StatusOrder(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return this.cmd;
    }
}
