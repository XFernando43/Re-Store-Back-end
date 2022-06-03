package com.example.prueba2.domain.values.Client;

import com.example.prueba2.application.notification.Notification;
import com.example.prueba2.application.notification.Result;
import lombok.Value;


import javax.persistence.Embeddable;

@Embeddable
@Value
public class Phone {
    private String value;
    private final static int MAX_LENGTH = 50;

    private Phone(String phone) {
        value = phone;
    }

    protected Phone() {
        this.value = "";
    }

    public static Result<Phone, Notification> create(String phone) {
        Notification notification = new Notification();
        phone = phone == null ? "" : phone.trim();
        if (phone.isEmpty()) {
            notification.addError("phone is required", null);
        }
        if (phone.length() > MAX_LENGTH) {
            notification.addError("The maximum length of a phone is " + MAX_LENGTH + " characters including spaces", null);
        }
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        return Result.success(new Phone(phone));
    }
}
