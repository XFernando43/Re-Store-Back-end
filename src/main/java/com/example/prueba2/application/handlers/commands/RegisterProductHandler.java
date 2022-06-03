package com.example.prueba2.application.handlers.commands;

import com.example.prueba2.application.commands.register.RegisterProduct;
import com.example.prueba2.application.notification.Notification;
import com.example.prueba2.application.notification.Result;
import com.example.prueba2.domain.entities.Product;
import com.example.prueba2.domain.enums.ProductState;
import com.example.prueba2.domain.values.Client.AuditTrail;
import com.example.prueba2.domain.values.Product.*;
import com.example.prueba2.repositories.ProductRepository;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegisterProductHandler {
    private ProductRepository productRepository;

    public RegisterProductHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @CommandHandler
    public Result<Product, Notification> handle(RegisterProduct registerProduct) {
        Notification notification = new Notification();
        ProductID productID = ProductID.of(UUID.fromString(registerProduct.getId()));
        Result<Price,Notification> priceResult=Price.create(registerProduct.getPrice());
        if(priceResult.isFailure()){
            notification.addError(priceResult.getFailure().errorMessage());
        }

        Result<Category, Notification> categoryResult= Category.create(registerProduct.getCategory());
        if(categoryResult.isFailure()){
            notification.addError(categoryResult.getFailure().errorMessage());
        }

        Result<Supplier, Notification> supplierResult = Supplier.create(registerProduct.getSupplier());
        if(supplierResult.isFailure()){
            notification.addError(supplierResult.getFailure().errorMessage());
        }

        Result<AuditTrail, Notification> auditTrailResult = AuditTrail.create(UUID.randomUUID());
        if (auditTrailResult.isFailure()) {
            notification.addError(auditTrailResult.getFailure().errorMessage());
        }

        Result<Description,Notification> DescriptionResult = Description.create(registerProduct.getDescription());
        if(DescriptionResult.isFailure()){
            notification.addError(DescriptionResult.getFailure().errorMessage());
        }

        Result<ProductName,Notification> productName = ProductName.create(registerProduct.getName());
        if(productName.isFailure()){
            notification.addError(productName.getFailure().errorMessage());
        }

        if (notification.hasErrors()) {
            return Result.failure(notification);
        }

        Product product= new Product(
                productID,
                productName.getSuccess(),
                categoryResult.getSuccess(),
                DescriptionResult.getSuccess(),
                priceResult.getSuccess(),
                supplierResult.getSuccess(),
                ProductState.INSTOCK,
                auditTrailResult.getSuccess());
        productRepository.save(product);
        return Result.success(product);
    }

}
