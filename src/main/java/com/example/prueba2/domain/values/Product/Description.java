package com.example.prueba2.domain.values.Product;

import com.example.prueba2.application.notification.Notification;
import com.example.prueba2.application.notification.Result;
import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Value
public class Description {
    private String value;
    private final static int MAX_LENGTH = 2500;

    private Description(String description) {
        value = description;
    }

    protected Description() {
        this.value = "";
    }

    public static Result<Description, Notification> create(String description){
        Notification notification= new Notification();
        description=description==null?"":description.trim();
        if (description.isEmpty()) {
            notification.addError(" name is required", null);
        }
        if (description.length() > MAX_LENGTH) {
            notification.addError("The maximum length of a name is " + MAX_LENGTH + " characters including spaces", null);
        }
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        return Result.success(new Description(description));
    }
}
