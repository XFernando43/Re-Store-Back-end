package com.example.prueba2.application.dtos.Response;

import lombok.Value;

@Value
public class EditCompanyResponse {
    private String id;
    private String ruc;
    private String name;
    private String address;
    private String email;
    private String phone;
}