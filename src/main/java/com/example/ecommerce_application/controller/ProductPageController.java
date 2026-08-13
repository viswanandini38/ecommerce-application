package com.example.ecommerce_application.controller;

import com.example.ecommerce_application.model.Product;
import com.example.ecommerce_application.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductPageController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products-page")
    public String viewProductsPage(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products"; // this maps to products.html in /templates
    }
}
