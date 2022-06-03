package com.example.prueba2.domain.values.Company;

import com.example.prueba2.application.notification.Notification;
import com.example.prueba2.application.notification.Result;
import lombok.Value;
import javax.persistence.Embeddable;

@Embeddable
@Value
public class Ruc {
    private String value;

    private final static int MAX_LENGTH = 11;

    private Ruc(String ruc) {
        value = ruc;
    }

    protected Ruc() {
        this.value = "";
    }

    public static Result<Ruc, Notification> create(String ruc) {
        Notification notification = new Notification();
        ruc = ruc == null ? "" : ruc.trim();
        if (ruc.isEmpty()) {
            notification.addError("ruc is required", null);
        }
        if (ruc.length() > MAX_LENGTH) {
            notification.addError("The maximum length of a ruc is " + MAX_LENGTH + " characters including spaces", null);
        }
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        return Result.success(new Ruc(ruc));
    }
}