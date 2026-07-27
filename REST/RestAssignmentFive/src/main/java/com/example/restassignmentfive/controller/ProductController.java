package com.example.restassignmentfive.controller;

import com.example.restassignmentfive.model.Product;
import com.example.restassignmentfive.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"}
    )
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping(
            value = "/{id}",
            produces = {"application/json", "application/xml"}
    )
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id)
                .map(product -> ResponseEntity.ok().body(product))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(produces = {"application/xml","application/json"})
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}