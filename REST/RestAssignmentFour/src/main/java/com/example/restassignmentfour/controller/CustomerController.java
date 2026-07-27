package com.example.restassignmentfour.controller;

import com.example.restassignmentfour.dto.ApiResponse;
import com.example.restassignmentfour.model.Customer;
import com.example.restassignmentfour.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Long id) {
        return customerService.getCustomerById(id)
                .map(customer -> ResponseEntity.ok().body((Object) customer))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("Customer with ID " + id + " not found")));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        return new ResponseEntity<>(new ApiResponse("Customer Added Successfully"), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer)
                .map(updated -> ResponseEntity.ok(new ApiResponse("Customer Updated Successfully")))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("Customer with ID " + id + " not found")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable("id") Long id) {
        if (customerService.deleteCustomer(id)) {
            return ResponseEntity.ok(new ApiResponse("Customer Deleted Successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Customer with ID " + id + " not found"));
        }
    }
}