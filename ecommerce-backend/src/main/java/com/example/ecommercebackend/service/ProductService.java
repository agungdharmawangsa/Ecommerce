package com.example.ecommercebackend.service;

import com.example.ecommercebackend.model.Product;
import com.example.ecommercebackend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProduct(){
        return productRepository.findAll();
    }

}
