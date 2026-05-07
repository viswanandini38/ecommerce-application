package com.example.ecommerce_application.repository;

import java.util.List;

import com.example.ecommerce_application.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findFirstByNameIgnoreCase(String keyword);
}