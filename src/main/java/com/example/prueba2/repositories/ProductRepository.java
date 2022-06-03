package com.example.prueba2.repositories;

import com.example.prueba2.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository <T extends Product> extends JpaRepository<T, UUID> {
        Optional<Product> findByProductID(String Id);
}

