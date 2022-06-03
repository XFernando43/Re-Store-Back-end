package com.example.prueba2.domain.values.Product;

import com.example.prueba2.application.notification.Notification;
import com.example.prueba2.application.notification.Result;
import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Value
public class Price {
    private String value;
    private final static int MAX_LENGTH = 10;
    private Price(String Price){value=Price;}
    protected Price(){this.value="";}

    public static Result<Price, Notification> create(String price){
        Notification notification= new Notification();
        price=price==null?"":price.trim();
        if (price.isEmpty()) {
            notification.addError("price is required", null);
        }
        if (price.length() > MAX_LENGTH) {
            notification.addError("The maximum length of a price is " + MAX_LENGTH + " characters including spaces", null);
        }
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        return Result.success(new Price(price));
    }
}
