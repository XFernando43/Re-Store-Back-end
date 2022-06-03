package com.example.prueba2.application.commands.register;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class RegisterProduct {
    @TargetAggregateIdentifier
    private String id;
    private String name;
    private String category;
    private String description;
    private String price;
    private String supplier;
    private String state;
    private String auditTrail;
}
