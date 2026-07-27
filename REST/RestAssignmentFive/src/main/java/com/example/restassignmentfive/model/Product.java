package com.example.restassignmentfive.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private Double price;

    public Product() {}

    public Product(String productName, Double price) {
        this.productName = productName;
        this.price = price;
    }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}