package com.example.ecommerce_application.repository;

import com.example.ecommerce_application.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
