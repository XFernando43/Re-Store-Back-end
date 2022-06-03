package com.example.prueba2.domain.values.Product;

import com.example.prueba2.application.notification.Notification;
import com.example.prueba2.application.notification.Result;
import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Value
public class Category {
    private String value;
    private final static int MAX_LENGTH = 50;
    private Category(String name) {
        value = name;
    }
    protected Category() {
        this.value = "";
    }
    public static Result<Category, Notification> create(String category){
        Notification notification= new Notification();
        category = category==null?"": category.trim();
        if (category.isEmpty()) {
            notification.addError("category name is required", null);
        }
        if (category.length() > MAX_LENGTH) {
            notification.addError("The maximum length of a category name is " + MAX_LENGTH + " characters including spaces", null);
        }
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        return Result.success(new Category(category));
    }

}
