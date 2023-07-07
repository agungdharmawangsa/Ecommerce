package com.example.ecommercebackend.repository;

import com.example.ecommercebackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
