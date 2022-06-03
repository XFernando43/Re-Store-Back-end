package com.example.prueba2.domain.enums;

public enum ProductState {
    INSTOCK(0),
    NOSTOCK(1);

    private final int value;

    ProductState(final int state) {
        value = state;
    }

    public int getValue() { return value; }
}
