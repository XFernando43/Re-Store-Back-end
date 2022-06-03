package com.example.prueba2.domain.values.Product;
import lombok.Value;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;
@Embeddable
@Value(staticConstructor = "of")
public class ProductID implements Serializable {
    private UUID value;
    private ProductID(UUID value) {
        this.value = value;
    }
    protected ProductID() {
        this.value = UUID.randomUUID();
    }
}
