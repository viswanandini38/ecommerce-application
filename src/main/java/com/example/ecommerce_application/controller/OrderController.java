package com.example.ecommerce_application.controller;

import com.example.ecommerce_application.model.CartItem;
import com.example.ecommerce_application.model.Order;
import com.example.ecommerce_application.repository.CartItemRepository;
import com.example.ecommerce_application.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    // Place Order (simulate payment)
    @PostMapping("/place")
    public String placeOrder() {
        List<CartItem> cartItems = cartItemRepository.findAll();
        if (cartItems.isEmpty()) {
            return "Cart is empty. Add items to cart before placing an order.";
        }

        for (CartItem item : cartItems) {
            Order order = new Order(
                item.getProductId(),
                item.getProductName(),
                item.getQuantity(),
                item.getPrice(),
                LocalDateTime.now()
            );
            orderRepository.save(order);
        }

        cartItemRepository.deleteAll();  // Clear cart after placing order
        return "Order placed successfully!";
    }

    // View all order history
    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
