package com.example.prueba2.application.validators;

import com.example.prueba2.application.dtos.Request.EditProductRequest;
import com.example.prueba2.application.notification.Notification;
import com.example.prueba2.domain.entities.Product;
import com.example.prueba2.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class EditProductValidator {

    private final ProductRepository productRepository;

    public EditProductValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Notification validate(EditProductRequest editProductRequest)
    {
        Notification notification = new Notification();
        String productId = editProductRequest.getId().trim();
        if (productId.isEmpty()) {
            notification.addError("Client id is required");
        }
        Optional<Product> productOptional = productRepository.findById(UUID.fromString(productId));
        if (productOptional.isPresent()) {
            notification.addError("Client not found");
            return notification;
        }

        String productName = editProductRequest.getName().trim();
        if (productName.isEmpty()) {
            notification.addError("Product Name is required");
        }

        String Price= editProductRequest.getPrice().trim();
        if(Price.isEmpty()){
            notification.addError("Product Price is required");
        }

        String Description = editProductRequest.getDescription().trim();
        if(Description.isEmpty()){
            notification.addError("Description is required");
        }

        String Supplier= editProductRequest.getSupplier().trim();
        if(Supplier.isEmpty()){
            notification.addError("Supplier is required");
        }

        String Category = editProductRequest.getCategory().trim();
        if (Category.isEmpty()) {
            notification.addError("Product Category is required");
        }

        String ID = editProductRequest.getId().trim();
        if (ID.isEmpty()) {
            notification.addError("Product Id is required");
        }

        if (notification.hasErrors()) {
            return notification;
        }
        productOptional = productRepository.findByProductID(ID);
        if (productOptional.isPresent()) {
            notification.addError("Product ID is taken");
        }
        return notification;
    }

}
