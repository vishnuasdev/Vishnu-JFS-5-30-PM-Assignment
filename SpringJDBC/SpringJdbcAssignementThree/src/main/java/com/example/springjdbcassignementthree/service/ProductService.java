package com.example.springjdbcassignementthree.service;

import com.example.springjdbcassignementthree.model.Product;
import com.example.springjdbcassignementthree.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void addProduct(Product product) {
        repository.save(product);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Optional<Product> getProductById(int productId) {
        return repository.findById(productId);
    }

    public boolean updateProductPrice(int productId, double newPrice) {
        return repository.updatePrice(productId, newPrice) > 0;
    }

    public boolean deleteProduct(int productId) {
        return repository.deleteById(productId) > 0;
    }

    public List<Product> getProductsByCategory(String category) {
        return repository.findByCategory(category);
    }

    public List<Product> getLowStockProducts(int threshold) {
        return repository.findByQuantityLessThan(threshold);
    }

    public double getTotalInventoryValue() {
        return repository.calculateTotalInventoryValue();
    }
}