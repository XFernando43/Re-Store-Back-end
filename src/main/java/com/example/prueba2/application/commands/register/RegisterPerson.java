package com.example.prueba2.application.commands.register;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class RegisterPerson {
    @TargetAggregateIdentifier
    private String id;
    private String dni;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phone;
}
