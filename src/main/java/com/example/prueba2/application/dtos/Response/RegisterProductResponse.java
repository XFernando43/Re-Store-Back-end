package com.example.prueba2.application.dtos.Response;

import lombok.Value;

@Value
public class RegisterProductResponse {
    private String id;
    private String name;
    private String category;
    private String description;
    private String price;
    private String supplier;
    private String state;
    private String auditTrail;
}
