package com.example.prueba2.application.dtos.Request;

import lombok.Value;

@Value
public class EditCompanyRequest {
    private String id;
    private String ruc;
    private String name;
    private String address;
    private String email;
    private String phone;
}