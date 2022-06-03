package com.example.prueba2.application.validators;

import com.example.prueba2.application.dtos.Request.RegisterProductRequest;
import com.example.prueba2.application.notification.Notification;
import com.example.prueba2.domain.entities.Product;
import com.example.prueba2.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RegisterProductValidator {
    private final ProductRepository productRepository;

    public RegisterProductValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Notification validate(RegisterProductRequest registerProductRequest)
    {
        Notification notification = new Notification();

        String ID = registerProductRequest.getId() != null ? registerProductRequest.getId().trim() : "";
        if (ID.isEmpty()) {
            notification.addError("Product Id is required");
        }

        String ProductName = registerProductRequest.getName() != null ? registerProductRequest.getName().trim() : "";
        if (ProductName.isEmpty()) {
            notification.addError("ProductName is required");
        }

        String Description = registerProductRequest.getDescription() != null ? registerProductRequest.getDescription().trim() : "";
        if (Description.isEmpty()) {
            notification.addError("Product Description is required");
        }

        String Category = registerProductRequest.getCategory() != null ? registerProductRequest.getCategory().trim() : "";
        if (Category.isEmpty()) {
            notification.addError("Product Category is required");
        }

        String Price = registerProductRequest.getPrice() != null ? registerProductRequest.getPrice().trim() : "";
        if (Price.isEmpty()) {
            notification.addError("Product Price is required");
        }

        String Supplier = registerProductRequest.getSupplier() != null ? registerProductRequest.getSupplier().trim() : "";
        if (Supplier.isEmpty()) {
            notification.addError("Product Supplier is required");
        }

        if (notification.hasErrors()) {
            return notification;
        }

        Optional<Product> productOptional = productRepository.findByProductID(ID);
        if (productOptional.isPresent()) {
            notification.addError("product id is taken");
        }

        return notification;
    }
}
