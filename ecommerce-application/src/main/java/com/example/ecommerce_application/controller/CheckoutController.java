package com.example.ecommerce_application.controller;

import com.example.ecommerce_application.model.CartItem;
import com.example.ecommerce_application.model.Product;
import com.example.ecommerce_application.repository.CartItemRepository;
import com.example.ecommerce_application.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/checkout")
@CrossOrigin
public class CheckoutController {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public Map<String, String> checkout() {

        Map<String, String> response = new HashMap<>();

        List<CartItem> items = cartItemRepository.findAll();

        if (items.isEmpty()) {

            response.put("status", "error");
            response.put("message", "Cart is empty.");

            return response;
        }

        for (CartItem item : items) {

            Product product = productRepository.findById(item.getProductId()).orElse(null);

            if (product == null) {

                response.put("status", "error");
                response.put("message", "Product not found.");

                return response;
            }

            if (product.getStockQuantity() < item.getQuantity()) {

                response.put("status", "error");
                response.put("message", "Not enough stock for " + product.getName());

                return response;
            }

            product.setStockQuantity(
                    product.getStockQuantity() - item.getQuantity());

            productRepository.save(product);
        }

        // Clear cart after payment
        cartItemRepository.deleteAll();

        response.put("status", "success");
        response.put("message", "Payment Successful! Order placed.");

        return response;
    }
}