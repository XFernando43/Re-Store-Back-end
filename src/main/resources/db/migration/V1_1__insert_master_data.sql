INSERT INTO client_types(id, name, created_at, created_by, updated_at, updated_by)
VALUES
    (1, 'Person', UTC_TIMESTAMP(), NULL, NULL, NULL),
    (2, 'Company', UTC_TIMESTAMP(), NULL, NULL, NULL);

INSERT INTO client_states(id, name, created_at, created_by, updated_at, updated_by)
VALUES
    (0, 'Inactive', UTC_TIMESTAMP(), NULL, NULL, NULL),
    (1, 'Active', UTC_TIMESTAMP(), NULL, NULL, NULL);

INSERT INTO Product_State_id(id, name, created_at, created_by, updated_at, updated_by)
VALUES
    (0, 'InStock', UTC_TIMESTAMP(), NULL, NULL, NULL),
    (1, 'NoStock', UTC_TIMESTAMP(), NULL, NULL, NULL);
