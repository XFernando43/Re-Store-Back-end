package com.example.prueba2.application.handlers.queries;

import com.example.prueba2.application.dtos.ProductView;
import com.example.prueba2.application.queries.GetProductByIdQuery;
import com.example.prueba2.domain.enums.ProductState;
import org.axonframework.queryhandling.QueryHandler;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class GetProductByIdHandler {
    @PersistenceContext
    private EntityManager entityManager;

    @QueryHandler
    public ProductView handle(GetProductByIdQuery query) {
        String sql = String.join(
                " ",
                "SELECT",
                "BIN_TO_UUID(id) AS id,",
                "dni AS dni,",
                "ProductName AS ProductName,",
                "Category AS Category,",
                "Description AS Description,",
                "Price AS Price,",
                "Supplier AS Supplier,",
                "ProductState AS ProductState",
                "date_format(created_at, '%Y-%m-%d %H:%i') AS createdAt",
                "FROM Products",
                "WHERE id = UUID_TO_BIN(:clientId) AND client_type_id = :clientTypeId"
        );
        return (ProductView) this.entityManager.createNativeQuery(sql)
                .setParameter("ProductId",query.getProductId())
                .setParameter("ProductType", ProductState.INSTOCK.getValue())
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(ProductView.class))
                .getSingleResult();
    }

}
