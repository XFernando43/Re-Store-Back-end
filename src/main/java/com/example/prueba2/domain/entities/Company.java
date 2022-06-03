package com.example.prueba2.domain.entities;

import com.example.prueba2.domain.values.Company.CompanyName;
import com.example.prueba2.domain.values.Company.Ruc;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@DiscriminatorValue(value="2")
public class Company extends Client {
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "value", column = @Column(name = "ruc", length = 11))
    })
    private Ruc ruc;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "value", column = @Column(name = "company_name", length = 150))
    })
    private CompanyName companyName;
}