package com.example.prueba2.application.dtos.Response;

import lombok.Value;

@Value
public class EditPersonResponse {
    private String id;
    private String dni;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phone;
}