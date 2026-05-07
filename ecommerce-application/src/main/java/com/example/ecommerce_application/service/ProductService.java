package com.example.ecommerce_application.service;

import com.example.ecommerce_application.model.Product;
import com.example.ecommerce_application.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product saveProduct(Product product) {
        return repo.save(product);
    }

    public Product getProductById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
