package com.example.prueba2.application.services;

import com.example.prueba2.application.commands.register.RegisterProduct;
import com.example.prueba2.application.dtos.ProductView;
import com.example.prueba2.application.dtos.Request.RegisterProductRequest;
import com.example.prueba2.application.dtos.Response.RegisterProductResponse;
import com.example.prueba2.application.enums.ResultType;
import com.example.prueba2.application.notification.Notification;
import com.example.prueba2.application.notification.Result;
import com.example.prueba2.application.queries.GetProductByIdQuery;
import com.example.prueba2.application.validators.EditProductValidator;
import com.example.prueba2.application.validators.RegisterProductValidator;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class ProductApplicationService {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final RegisterProductValidator registerProductValidator;
    private final EditProductValidator editProductValidator;

    public ProductApplicationService(RegisterProductValidator registerProductValidator, EditProductValidator editProductValidator, CommandGateway commandGateway, QueryGateway queryGateway) {
        this.registerProductValidator = registerProductValidator;
        this.editProductValidator = editProductValidator;
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    public Result<RegisterProductResponse, Notification> registerProduct(RegisterProductRequest registerProductRequest) throws Exception {
        Notification notification = this.registerProductValidator.validate(registerProductRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        String productId = UUID.randomUUID().toString();
        RegisterProduct registerProduct = new RegisterProduct(
                productId,
                registerProductRequest.getName().trim(),
                registerProductRequest.getCategory().trim(),
                registerProductRequest.getDescription().trim(),
                registerProductRequest.getPrice().trim(),
                registerProductRequest.getSupplier().trim(),
                registerProductRequest.getState().trim(),
                registerProductRequest.getAuditTrail().trim()
        );
        CompletableFuture<Object> future = commandGateway.send(registerProduct);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        RegisterProductResponse registerProductResponse = new RegisterProductResponse(
                registerProduct.getId(),
                registerProduct.getName(),
                registerProduct.getCategory(),
                registerProduct.getDescription(),
                registerProduct.getPrice(),
                registerProduct.getSupplier(),
                registerProduct.getState(),
                registerProduct.getAuditTrail()
        );
        return Result.success(registerProductResponse);
    }

    public ProductView getById(String productID) throws Exception {
        CompletableFuture<ProductView> future = queryGateway.query(new GetProductByIdQuery(productID), ResponseTypes.instanceOf(ProductView.class));
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        return future.get();
    }

}
