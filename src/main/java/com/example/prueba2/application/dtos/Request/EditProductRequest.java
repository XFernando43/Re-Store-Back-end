package com.example.prueba2.application.dtos.Request;

import lombok.Data;

@Data
public class EditProductRequest {
    private String id;
    private String name;
    private String category;
    private String description;
    private String price;
    private String supplier;
    private String state;
    private String auditTrail;
}
