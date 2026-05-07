package com.example.ecommerce_application.config;

import com.example.ecommerce_application.model.Product;
import com.example.ecommerce_application.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.event.ApplicationReadyEvent;

@Component
public class DataLoader {

    @Autowired
    private ProductRepository productRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        try {
            if (productRepository.count() == 0) {
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
            	        "http://localhost:9090/uploads/powerbank.jpg", "powerbank.jpg"));
            	productRepository.save(new Product("Webcam", 2500f, 18, "HD USB Webcam",
            	        "http://localhost:9090/uploads/webcam.jpg", "webcam.jpg"));
            	productRepository.save(new Product("Charger", 1000f, 30, "USB-C Fast Charger",
            	        "http://localhost:9090/uploads/charger.jpg", "charger.jpg"));
            	productRepository.save(new Product("External HDD", 4500f, 15, "1TB Portable HDD",
            	        "http://localhost:9090/uploads/hdd.jpg", "hdd.jpg"));
            	productRepository.save(new Product("Router", 3000f, 20, "Dual Band Wi-Fi Router",
            	        "http://localhost:9090/uploads/router.jpg", "router.jpg"));
            	productRepository.save(new Product("Microphone", 3500f, 10, "USB Podcast Mic",
            	        "http://localhost:9090/uploads/microphone.jpg", "microphone.jpg"));
            	productRepository.save(new Product("Graphics Card", 25000f, 5, "NVIDIA GeForce GTX",
            	        "http://localhost:9090/uploads/graphicscard.jpg", "graphicscard.jpg"));
            	productRepository.save(new Product("Gaming Chair", 8000f, 7, "Ergonomic Racing Chair",
            	        "http://localhost:9090/uploads/gamingchair.jpg", "gamingchair.jpg"));
            	productRepository.save(new Product("Desk Lamp", 700f, 25, "LED Adjustable Desk Lamp",
            	        "http://localhost:9090/uploads/desklamp.jpg", "desklamp.jpg"));
            	productRepository.save(new Product("VR Headset", 32000f, 3, "Oculus Quest 2",
            	        "http://localhost:9090/uploads/vrheadset.jpg", "vrheadset.jpg"));
            	productRepository.save(new Product("Smart TV", 40000f, 4, "Samsung 43-inch 4K UHD",
            	        "http://localhost:9090/uploads/smarttv.jpg", "smarttv.jpg"));

        
                System.out.println("✅ Products loaded into the database.");
            } else {
                System.out.println("ℹ️ Products already exist. Skipping load.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error during data loading: " + e.getMessage());
        }
    }
}
