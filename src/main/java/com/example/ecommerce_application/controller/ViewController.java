package com.example.ecommerce_application.controller;

import com.example.ecommerce_application.model.Product;
import com.example.ecommerce_application.model.CartItem;
import com.example.ecommerce_application.repository.ProductRepository;
import com.example.ecommerce_application.repository.CartItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ViewController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    // 🔍 Search or list all products (used in products.html)
    @GetMapping("/view/all-products")
    public String showProducts(@RequestParam(value = "query", required = false) String query, Model model) {
        List<Product> products;

        if (query != null && !query.isEmpty()) {
            products = productRepository.findByNameContainingIgnoreCase(query);
        } else {
            products = productRepository.findAll();
        }

        model.addAttribute("products", products);
        return "products"; // templates/products.html
    }

    // 📦 Catalog view (used in catalog.html)
    @GetMapping("/catalog")
    public String showCatalog(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "catalog"; // templates/catalog.html
    }

    // 🏠 Main homepage view (used in product.html)
    @GetMapping("/view/products")
    public String viewProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "product"; // templates/product.html
    }

    // 🧾 Checkout view (used in checkout.html)
    @GetMapping("/checkout")
    public String showCheckoutPage(Model model) {
        List<CartItem> cartItems = cartItemRepository.findAll();

        float total = 0;
        for (CartItem item : cartItems) {
            total += item.getQuantity() * item.getPrice();
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", total);

        return "checkout"; // templates/checkout.html
    }
}
