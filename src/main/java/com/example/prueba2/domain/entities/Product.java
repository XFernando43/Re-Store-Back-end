package com.example.prueba2.domain.entities;

import com.example.prueba2.domain.enums.ClientState;
import com.example.prueba2.domain.enums.ProductState;
import com.example.prueba2.domain.values.Client.Address;
import com.example.prueba2.domain.values.Client.AuditTrail;
import com.example.prueba2.domain.values.Client.ClientId;
import com.example.prueba2.domain.values.Product.*;
import lombok.Data;
import org.axonframework.modelling.command.AggregateIdentifier;

import javax.persistence.*;

@Entity(name = "Product")
@Table(name = "products")
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        discriminatorType = DiscriminatorType.INTEGER,
        name = "Product_type_id",
        columnDefinition = "TINYINT(2)"
)
public class Product {

    @AggregateIdentifier
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "ProductId", columnDefinition = "BINARY(16)"))
    })
    private ProductID id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "ProductName", length = 100, nullable = false))
    })
    private ProductName name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "CategoryProduct", length = 50, nullable = false))
    })
    private Category category;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "ProductDescription", length = 2500, nullable = false))
    })
    private Description description;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "ProductPrice", length = 10, nullable = false))
    })
    private Price price;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "ProductSupplier", length = 100, nullable = false))
    })
    private Supplier supplier;

    @Column(name = "Product_State_id", columnDefinition = "TINYINT(1) UNSIGNED", nullable = false)
    private ProductState state;

    @Embedded
    private AuditTrail auditTrail;


    public Product(ProductID id, ProductName name, Category category, Description description, Price price,
                   Supplier supplier, ProductState state, AuditTrail auditTrail) {
        setId(id);
        setName(name);
        setCategory(category);
        setDescription(description);
        setPrice(price);
        setSupplier(supplier);
        setState(state);
        setAuditTrail(auditTrail);
    }

    protected Product(){

    }
}
