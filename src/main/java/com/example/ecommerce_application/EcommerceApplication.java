package com.example.ecommerce_application;

import com.example.ecommerce_application.model.Product;
import com.example.ecommerce_application.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    // Load sample products when the app starts
    @Bean
    CommandLineRunner loadData(ProductRepository productRepository) {
        return args -> {
        	productRepository.save(new Product("Laptop", 55000f, 10, "Dell Inspiron 15",
        	        "http://localhost:9090/uploads/laptop.jpg", "laptop.jpg"));
        	productRepository.save(new Product("Smartphone", 25000f, 20, "Samsung Galaxy A52",
        	        "http://localhost:9090/uploads/smartphone.jpg", "smartphone.jpg"));
        	productRepository.save(new Product("Headphones", 3000f, 50, "Noise Cancelling",
        	        "http://localhost:9090/uploads/headphones.jpg", "headphones.jpg"));
        	productRepository.save(new Product("Smartwatch", 7000f, 15, "Fitbit Versa",
        	        "http://localhost:9090/uploads/smartwatch.jpg", "smartwatch.jpg"));
        	productRepository.save(new Product("Keyboard", 1200f, 30, "Mechanical RGB Keyboard",
        	        "http://localhost:9090/uploads/keyboard.jpg", "keyboard.jpg"));
        	productRepository.save(new Product("Mouse", 800f, 40, "Wireless Optical Mouse",
        	        "http://localhost:9090/uploads/mouse.jpg", "mouse.jpg"));
        	productRepository.save(new Product("Monitor", 15000f, 8, "24-inch LED Monitor",
        	        "http://localhost:9090/uploads/monitor.jpg", "monitor.jpg"));
        	productRepository.save(new Product("Tablet", 18000f, 12, "Lenovo Tab M10",
        	        "http://localhost:9090/uploads/tablet.jpg", "tablet.jpg"));
        	productRepository.save(new Product("Speaker", 2200f, 25, "Bluetooth Portable Speaker",
        	        "http://localhost:9090/uploads/speaker.jpg", "speaker.jpg"));
        	productRepository.save(new Product("Power Bank", 1500f, 35, "10000mAh Fast Charging",
        	        "http://localhost:9090/uploads/Power Bank.jpg", "Power Bank.jpg"));
        	productRepository.save(new Product("Webcam", 2500f, 18, "HD USB Webcam",
        	        "http://localhost:9090/uploads/Webcam.jpg", "Webcam.jpg"));
        	productRepository.save(new Product("Charger", 1000f, 30, "USB-C Fast Charger",
        	        "http://localhost:9090/uploads/Charger.jpg", "Charger.jpg"));
        	productRepository.save(new Product("External HDD", 4500f, 15, "1TB Portable HDD",
        	        "http://localhost:9090/uploads/External HDD.jpg", "ExternalHDD.jpg"));
        	productRepository.save(new Product("Router", 3000f, 20, "Dual Band Wi-Fi Router",
        	        "http://localhost:9090/uploads/Router.jpg", "Router.jpg"));
        	productRepository.save(new Product("Microphone", 3500f, 10, "USB Podcast Mic",
        	        "http://localhost:9090/uploads/Microphone.jpg", "Microphone.jpg"));
        	productRepository.save(new Product("Graphics Card", 25000f, 5, "NVIDIA GeForce GTX",
        	        "http://localhost:9090/uploads/Graphics card.jpg", "Graphics card.jpg"));
        	productRepository.save(new Product("Gaming Chair", 8000f, 7, "Ergonomic Racing Chair",
        	        "http://localhost:9090/uploads/Gaming chair.jpg", "Gaming chair.jpg"));
        	productRepository.save(new Product("Desk Lamp", 700f, 25, "LED Adjustable Desk Lamp",
        	        "http://localhost:9090/uploads/Desk Lamp.jpg", "Desk Lamp.jpg"));
        	productRepository.save(new Product("VR Headset", 32000f, 3, "Oculus Quest 2",
        	        "http://localhost:9090/uploads/VR Headset.jpg", "VR Headset.jpg"));
        	productRepository.save(new Product("Smart TV", 40000f, 4, "Samsung 43-inch 4K UHD",
        	        "http://localhost:9090/uploads/Smart Tv.jpg", "Smart Tv.jpg"));
        	productRepository.save(new Product("T-Shirt", 499f, 50, "Cotton round neck T-Shirt",
        	        "http://localhost:9090/uploads/tshirt.jpg", "tshirt.jpg"));
        	productRepository.save(new Product("Jeans", 1199f, 40, "Slim-fit denim jeans",
        	        "http://localhost:9090/uploads/jeans.jpg", "jeans.jpg"));
        	productRepository.save(new Product("Women's Sandals", 1499f, 25, "Flat strappy sandals",
        	        "http://localhost:9090/uploads/sandals.jpg", "sandals.jpg"));
        	productRepository.save(new Product("Men's Formal Shoes", 3499f, 20, "Leather Oxford shoes",
        	        "http://localhost:9090/uploads/formal_shoes.jpg", "formal_shoes.jpg"));
        	productRepository.save(new Product("Hoodie", 999f, 35, "Unisex winter hoodie",
        	        "http://localhost:9090/uploads/hoodie.jpg", "hoodie.jpg"));
        	productRepository.save(new Product("Handbag", 2499f, 15, "Women's leather handbag",
        	        "http://localhost:9090/uploads/handbag.jpg", "handbag.jpg"));
        	productRepository.save(new Product("Watch", 1999f, 18, "Analog wrist watch",
        	        "http://localhost:9090/uploads/watch.jpg", "watch.jpg"));
        	productRepository.save(new Product("Sunglasses", 899f, 30, "UV-protected aviators",
        	        "http://localhost:9090/uploads/sunglasses.jpg", "sunglasses.jpg"));
        	productRepository.save(new Product("Kurta", 1299f, 25, "Cotton printed kurta",
        	        "http://localhost:9090/uploads/kurta.jpg", "kurta.jpg"));
        	productRepository.save(new Product("Sweater", 1099f, 22, "Knitted winter sweater",
        	        "http://localhost:9090/uploads/sweater.jpg", "sweater.jpg"));
        	productRepository.save(new Product("Scarf", 349f, 30, "Woolen printed scarf",
        	        "http://localhost:9090/uploads/scarf.jpg", "scarf.jpg"));
        	productRepository.save(new Product("Belt", 599f, 28, "Leather formal belt",
        	        "http://localhost:9090/uploads/belt.jpg", "belt.jpg"));
        	productRepository.save(new Product("Ethnic Dress", 2299f, 12, "Women's Anarkali suit",
        	        "http://localhost:9090/uploads/ethnic_dress.jpg", "ethnic_dress.jpg"));
        };	
    }
  
}

