package com.example.ecommerce_application.controller;

import com.example.ecommerce_application.model.CartItem;
import com.example.ecommerce_application.model.Product;
import com.example.ecommerce_application.repository.ProductRepository;
import com.example.ecommerce_application.service.CartService;
import jakarta.servlet.http.HttpSession;
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
    private CartService cartService;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public Map<String, String> checkout(HttpSession session) {
        Map<String, String> response = new HashMap<>();

        List<CartItem> items = cartService.getCartItems(session);

        if (items.isEmpty()) {
            response.put("status", "error");
            response.put("message", "Cart is empty. Add items before checkout.");
            return response;
        }

        for (CartItem item : items) {
            Product product = productRepository.findById(item.getProductId()).orElse(null);
            if (product == null) {
                response.put("status", "error");
                response.put("message", "Product not found: " + item.getProductId());
                return response;
            }

            if (product.getStockQuantity() < item.getQuantity()) {
                response.put("status", "error");
                response.put("message", "Not enough stock for product: " + product.getName());
                return response;
            }

            product.setStockQuantity(product.getStockQuantity() - item.getQuantity());
            productRepository.save(product);
        }

        cartService.clearCart(session);
        response.put("status", "success");
        response.put("message", "Checkout successful! Payment simulated.");

        return response;
    }
}
