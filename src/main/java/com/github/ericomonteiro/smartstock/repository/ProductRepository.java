package com.github.ericomonteiro.smartstock.repository;

import com.github.ericomonteiro.smartstock.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
