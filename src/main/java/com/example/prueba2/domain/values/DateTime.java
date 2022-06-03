package com.example.prueba2.domain.values;

import lombok.Value;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Value
public class DateTime {
    private LocalDateTime value;

    private DateTime(LocalDateTime dateTime) {
        this.value = dateTime;
    }

    protected DateTime() {
        this.value = null;
    }
}
