package com.example.prueba2.application.handlers.queries;

import com.example.prueba2.application.dtos.PersonView;
import com.example.prueba2.application.queries.GetClientPersonByIdQuery;
import com.example.prueba2.domain.enums.ClientType;
import org.axonframework.queryhandling.QueryHandler;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class GetPersonByIdHandler {
    @PersistenceContext
    private EntityManager entityManager;

    @QueryHandler
    public PersonView handle(GetClientPersonByIdQuery query) {
        String sql = String.join(
            " ",
            "SELECT",
            "BIN_TO_UUID(id) AS clientId,",
            "dni AS dni,",
            "first_name AS firstName,",
            "last_name AS lastName,",
            "address AS address,",
            "email AS email,",
            "phone AS phone,",
            "date_format(created_at, '%Y-%m-%d %H:%i') AS createdAt",
            "FROM clients",
            "WHERE id = UUID_TO_BIN(:clientId) AND client_type_id = :clientTypeId"
        );

        return (PersonView) this.entityManager.createNativeQuery(sql)
            .setParameter("clientId", query.getClientId())
            .setParameter("clientTypeId", ClientType.PERSON.getValue())
            .unwrap(org.hibernate.query.NativeQuery.class)
            .setResultTransformer(Transformers.aliasToBean(PersonView.class))
            .getSingleResult();
    }
}
