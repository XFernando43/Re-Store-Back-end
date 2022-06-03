package com.example.prueba2.application.queries;

import lombok.Data;

@Data
public class GetClientPersonByIdQuery {
    private String clientId;

    public GetClientPersonByIdQuery(String clientId) {
        this.clientId = clientId;
    }
}
