package com.example.ecommerce_application.controller;

import com.example.ecommerce_application.model.User;
import com.example.ecommerce_application.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Show signup page
    @GetMapping("/signup")
    public String signupPage(Model model) {

        model.addAttribute("user", new User());

        return "signup";
    }

    // Handle signup form
    @PostMapping("/signup")
    public String registerUser(@ModelAttribute User user) {

        // Check existing email
        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null) {
            return "redirect:/signup?error";
        }

        // Encrypt password
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        // Save user
        userRepository.save(user);

        return "redirect:/login";
    }

    // Show login page
    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }
}