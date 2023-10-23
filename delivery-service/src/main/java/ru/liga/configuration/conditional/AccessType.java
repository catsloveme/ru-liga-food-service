package ru.liga.configuration.conditional;

public enum AccessType {
    JPA("jpa"),
    My_Batis("my_batis");

    AccessType(String implementation) {
    }
}
