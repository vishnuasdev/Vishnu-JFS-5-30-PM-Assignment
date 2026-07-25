package com.example.springjdbcassignementthree;

import com.example.springjdbcassignementthree.model.Product;
import com.example.springjdbcassignementthree.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringJdbcAssignementThreeApplication implements CommandLineRunner {

    private final ProductService service;

    public SpringJdbcAssignementThreeApplication(ProductService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcAssignementThreeApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("\n-----------------------------------------------------------------------------------------");
        System.out.println("                   PRODUCT INVENTORY MANAGEMENT SYSTEM (SPRING JDBC)                      ");
        System.out.println("-----------------------------------------------------------------------------------------\n");

        // View All Products
        System.out.println("--- All Inventory Products (Initial 15 Records) ---");
        displayProducts(service.getAllProducts());

        // Add New Product -> Display ONLY the newly added record
        System.out.println("\n--- Adding New Product ---");
        Product newProduct = new Product("Noise Cancelling Headset", "Electronics", 4999.00, 14);
        service.addProduct(newProduct);
        System.out.println("Product Added Successfully!");

        List<Product> allProducts = service.getAllProducts();
        if (!allProducts.isEmpty()) {
            Product added = allProducts.get(allProducts.size() - 1);
            System.out.println("Added Record: " + added);
        }

        // Search Product by ID
        System.out.println("\n--- Searching Product by ID (ID: 4) ---");
        service.getProductById(4).ifPresentOrElse(
                prod -> System.out.println("Found: " + prod),
                () -> System.out.println("Product not found.")
        );

        // Update Product Price -> Display ONLY the updated record
        System.out.println("\n--- Updating Price for Product ID: 2 to ₹2,199.00 ---");
        if (service.updateProductPrice(2, 2199.00)) {
            System.out.println("Price Updated Successfully!");
            service.getProductById(2).ifPresent(prod -> System.out.println("Updated Record: " + prod));
        }

        // Delete Product
        System.out.println("\n--- Deleting Product ID: 9 ---");
        if (service.deleteProduct(9)) {
            System.out.println("Product ID 9 Deleted Successfully from Database.");
        }

        // Search Products by Category
        System.out.println("\n--- Products in Category: 'Electronics' ---");
        displayProducts(service.getProductsByCategory("Electronics"));

        // Display Low Stock Products (Quantity < 10)
        System.out.println("\n--- Low Stock Alert (Quantity < 10) ---");
        displayProducts(service.getLowStockProducts(10));

        // Total Inventory Value Calculation
        System.out.println("\n--- Overall Inventory Financials ---");
        double totalVal = service.getTotalInventoryValue();
        System.out.printf("Total Total Valuation of Stock in Hand: ₹%,.2f\n", totalVal);

        System.out.println("\n-----------------------------------------------------------------------------------------");
    }

    private void displayProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (Product p : products) {
                System.out.println(p);
            }
        }
    }
}