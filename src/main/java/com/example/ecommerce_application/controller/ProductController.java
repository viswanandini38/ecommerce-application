package com.example.ecommerce_application.controller;

import com.example.ecommerce_application.model.Product;
import com.example.ecommerce_application.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin // Allows frontend (if separate) to access these APIs
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // ✅ CREATE product
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // ✅ READ ALL products
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // ✅ READ ONE product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ SEARCH products by name (case-insensitive)
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String query) {
        return productRepository.findByNameContainingIgnoreCase(query);
    }

    // ✅ UPDATE product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return productRepository.findById(id).map(product -> {
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            product.setStockQuantity(updatedProduct.getStockQuantity());
            product.setImageUrl(updatedProduct.getImageUrl());
            return ResponseEntity.ok(productRepository.save(product));
        }).orElse(ResponseEntity.notFound().build());
    }

    // ✅ DELETE product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ UPLOAD IMAGE and attach to Product
    @PostMapping("/{id}/image")
    public ResponseEntity<String> uploadProductImage(@PathVariable Long id,
                                                     @RequestParam("file") MultipartFile file) {
        try {
            Optional<Product> productOpt = productRepository.findById(id);
            if (!productOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
            }

            // Check for image file type
            if (!file.getContentType().startsWith("image/")) {
                return ResponseEntity.badRequest().body("Only image files are allowed.");
            }

            // Save file to local directory
            String uploadsDir = System.getProperty("user.dir") + "/uploads/";
            File uploadFolder = new File(uploadsDir);
            if (!uploadFolder.exists()) uploadFolder.mkdirs();

            String filename = Path.of(file.getOriginalFilename()).getFileName().toString();
            Path filepath = Paths.get(uploadsDir, filename);
            Files.write(filepath, file.getBytes());

            // Update product's imageUrl
            Product product = productOpt.get();
            product.setImageUrl("/images/" + filename);
            productRepository.save(product);

            return ResponseEntity.ok("Image uploaded and product updated: " + filename);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed.");
        }
    }
}
