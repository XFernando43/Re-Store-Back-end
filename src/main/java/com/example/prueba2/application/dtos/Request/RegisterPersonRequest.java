package com.example.prueba2.application.dtos.Request;

import lombok.Value;

@Value
public class RegisterPersonRequest {
    private String dni;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phone;
}