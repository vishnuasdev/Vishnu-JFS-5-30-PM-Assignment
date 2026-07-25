package com.example.springjdbcassignementthree.repository;

import com.example.springjdbcassignementthree.mapper.ProductRowMapper;
import com.example.springjdbcassignementthree.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Add Product (Parameterized Query)
    public int save(Product product) {
        String sql = "INSERT INTO products (product_name, category, price, quantity) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, product.getProductName(), product.getCategory(), product.getPrice(), product.getQuantity());
    }

    // View All Products
    public List<Product> findAll() {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    // Search Product by ID (Parameterized Query)
    public Optional<Product> findById(int productId) {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        List<Product> list = jdbcTemplate.query(sql, new ProductRowMapper(), productId);
        return list.stream().findFirst();
    }

    // Update Product Price (Parameterized Query)
    public int updatePrice(int productId, double newPrice) {
        String sql = "UPDATE products SET price = ? WHERE product_id = ?";
        return jdbcTemplate.update(sql, newPrice, productId);
    }

    // Delete Product (Parameterized Query)
    public int deleteById(int productId) {
        String sql = "DELETE FROM products WHERE product_id = ?";
        return jdbcTemplate.update(sql, productId);
    }

    // Find Products by Category (Parameterized Query)
    public List<Product> findByCategory(String category) {
        String sql = "SELECT * FROM products WHERE category = ?";
        return jdbcTemplate.query(sql, new ProductRowMapper(), category);
    }

    // Display Products with Quantity Less Than Threshold (Low Stock)
    public List<Product> findByQuantityLessThan(int threshold) {
        String sql = "SELECT * FROM products WHERE quantity < ?";
        return jdbcTemplate.query(sql, new ProductRowMapper(), threshold);
    }

    // Calculate Total Inventory Value (SUM of price * quantity)
    public Double calculateTotalInventoryValue() {
        String sql = "SELECT SUM(price * quantity) FROM products";
        Double total = jdbcTemplate.queryForObject(sql, Double.class);
        return total != null ? total : 0.0;
    }
}