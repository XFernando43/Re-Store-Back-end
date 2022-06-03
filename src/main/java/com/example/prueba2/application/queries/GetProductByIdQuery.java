package com.example.prueba2.application.queries;

import lombok.Data;

@Data
public class GetProductByIdQuery {
    private String productId;
    public GetProductByIdQuery(String productId) {
        this.productId = productId;
    }
}
