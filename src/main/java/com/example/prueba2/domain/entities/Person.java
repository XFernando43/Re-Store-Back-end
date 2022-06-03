package com.example.prueba2.domain.entities;

import com.example.prueba2.domain.enums.ClientState;
import com.example.prueba2.domain.values.Client.*;
import com.example.prueba2.domain.values.Person.Dni;
import com.example.prueba2.domain.values.Person.FullName;
import lombok.Data;


import javax.persistence.*;

@Entity
@Data
@DiscriminatorValue(value="1")
public class Person extends Client {
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "value", column = @Column(name = "dni", length = 8))
    })
    private Dni dni;

    @Embedded
    private FullName fullName;

    public Person(ClientId clientId, Address address, Email email, Phone phone, ClientState state, AuditTrail auditTrail, Dni dni, FullName fullName) {
        super(clientId, address, email, phone, state, auditTrail);
        setDni(dni);
        setFullName(fullName);
    }

    protected Person() {
    }
}
