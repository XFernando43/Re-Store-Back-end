package com.example.prueba2.repositories;

import com.example.prueba2.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository<T extends Client> extends JpaRepository<T, UUID> {

}
