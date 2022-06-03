package com.example.prueba2.application.dtos;

import lombok.Data;

@Data
public class ProductView {
    private String id;
    private String name;
    private String category;
    private String description;
    private String price;
    private String supplier;
    private String state;
    private String auditTrail;

}
