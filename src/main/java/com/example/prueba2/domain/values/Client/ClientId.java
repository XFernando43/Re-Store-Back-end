package com.example.prueba2.domain.values.Client;

import lombok.Value;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Value(staticConstructor = "of")
public class ClientId implements Serializable {
    private UUID value;

    private ClientId(UUID value) {
        this.value = value;
    }

    protected ClientId() {
        this.value = UUID.randomUUID();
    }
}