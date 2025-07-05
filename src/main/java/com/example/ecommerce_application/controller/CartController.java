package com.example.ecommerce_application.controller;

import com.example.ecommerce_application.model.CartItem;
import com.example.ecommerce_application.model.Product;
import com.example.ecommerce_application.repository.CartItemRepository;
import com.example.ecommerce_application.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CartController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    // ✅ Add product to cart by product ID
    @GetMapping("/cart/add/{productId}")
    public String addToCart(@PathVariable Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            return "redirect:/view/products"; // Or show error page
        }

        Product product = optionalProduct.get();

        Optional<CartItem> optionalCartItem = cartItemRepository.findFirstByProductId(productId);

        if (optionalCartItem.isPresent()) {
            CartItem existingItem = optionalCartItem.get();
            existingItem.setQuantity(existingItem.getQuantity() + 1);
            cartItemRepository.save(existingItem);
        } else {
            CartItem newItem = new CartItem(
                    product.getId(),
                    product.getName(),
                    1,
                    product.getPrice()
            );
            cartItemRepository.save(newItem);
        }

        return "redirect:/cart";
    }

    // ✅ Show cart page
    @GetMapping("/cart")
    public String viewCart(Model model) {
        List<CartItem> cartItems = cartItemRepository.findAll();
        float total = 0f;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);
        return "cart"; // cart.html
    }

    // ✅ Remove a specific item from cart
    @PostMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable Long id) {
        cartItemRepository.deleteById(id);
        return "redirect:/cart";
    }

    // ✅ Clear all items from cart
    @PostMapping("/cart/clear")
    public String clearCart() {
        cartItemRepository.deleteAll();
        return "redirect:/cart";
    }
}
