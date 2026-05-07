package com.example.ecommerce_application.controller;

import com.example.ecommerce_application.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin
public class PaymentController {

    @Autowired
    private CartItemRepository cartItemRepository;

    @PostMapping("/checkout")
    public String simulatePayment() {
        cartItemRepository.deleteAll(); // Clear the cart after "payment"
        return "✅ Payment Successful! Your order has been placed.";
    }
}
