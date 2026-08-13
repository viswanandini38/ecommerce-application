package com.example.ecommerce_application.repository;

import com.example.ecommerce_application.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findFirstByProductId(Long productId);
}
