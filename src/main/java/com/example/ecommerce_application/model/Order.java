package com.example.ecommerce_application.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private String productName;
    private int quantity;
    private float price;
    private LocalDateTime orderDate;

    public Order() {}

    public Order(Long productId, String productName, int quantity, float price, LocalDateTime orderDate) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.orderDate = orderDate;
    }

    // Getters and Setters
    // (Generate using IDE or write manually)
    public Long getId()
    {
    	return id;
    }
    public void setId(Long id) {
    	this.id=id;
    }
    public Long getProductId() {
    	return productId;
    }
    public void setProductId(Long productId) {
    	this.productId=productId;
    }
    public String getProductName() {
    	return productName;
    }
    public void setProductName(String productName) {
    	this.productName=productName;
    }
    public int getQuantity()
    {
    	return quantity;
    }
    public void setQuantity(int quantity)
    {
    	this.quantity=quantity;
    }
    public float getPrice() {
    	return price;
    }
    public void setPrice(float price) {
    	this.price=price;
    }
    public LocalDateTime getOrderDate()
    {
    	return orderDate;
    }
    public void setOrderDate(LocalDateTime orderDate) {
    	this.orderDate=orderDate;
    }
}
    
