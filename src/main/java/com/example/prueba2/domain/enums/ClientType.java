package com.example.prueba2.domain.enums;

public enum ClientType {
    PERSON(1),
    COMPANY(2);

    private final int value;

    ClientType(final int type) {
        value = type;
    }

    public int getValue() { return value; }
}
