package com.example.prueba2.repositories;

import com.example.prueba2.domain.entities.Person;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends ClientRepository<Person> {
    Optional<Person> findByDniValue(String dni);
}