package com.github.ericomonteiro.smartstock.repository;

import com.github.ericomonteiro.smartstock.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByNameIgnoreCase (String name);
    Optional<Product> findByNameIgnoreCaseAndIdIsNot(String name, Long id);
}
