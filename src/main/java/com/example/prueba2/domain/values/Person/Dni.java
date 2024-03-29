package com.example.prueba2.domain.values.Person;

import com.example.prueba2.application.notification.Notification;
import com.example.prueba2.application.notification.Result;
import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Value
public class Dni {
    private String value;

    private final static int MAX_LENGTH = 8;

    private Dni(String dni) {
        value = dni;
    }

    protected Dni() {
        this.value = "";
    }

    public static Result<Dni, Notification> create(String dni) {
        Notification notification = new Notification();
        dni = dni == null ? "" : dni.trim();
        if (dni.isEmpty()) {
            notification.addError("dni is required", null);
        }
        if (dni.length() > MAX_LENGTH) {
            notification.addError("The maximum length of a dni is " + MAX_LENGTH + " characters including spaces", null);
        }
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        return Result.success(new Dni(dni));
    }
}