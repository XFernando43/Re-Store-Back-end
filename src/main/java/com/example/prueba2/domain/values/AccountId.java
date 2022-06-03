package com.example.prueba2.domain.values;

import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Value
public class AccountId {
    private long value;

    private AccountId(long value) {
        this.value = value;
    }

    protected AccountId() {
        this.value = 0;
    }
}
