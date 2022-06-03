package com.example.prueba2.domain.values.Product;

import com.example.prueba2.application.notification.Notification;
import com.example.prueba2.application.notification.Result;
import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Value
public class ProductName {
    private String value;

    private final static int MAX_LENGTH = 100;

    private ProductName(String name) {
        value = name;
    }

    protected ProductName() {this.value = "";}

    public static Result<ProductName,Notification> create(String name){
        Notification notification = new Notification();
        name=name==null? "":name.trim();
        if (name.isEmpty()) {
            notification.addError("product name is required", null);
        }
        if (name.length() > MAX_LENGTH) {
            notification.addError("The maximum length of a product name is " + MAX_LENGTH + " characters including spaces", null);
        }
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        return Result.success(new ProductName(name));
    }

}
