package com.example.restassignmentfour.service;

import com.example.restassignmentfour.model.Customer;
import com.example.restassignmentfour.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> updateCustomer(Long id, Customer updatedCustomer) {
        return customerRepository.findById(id).map(existingCustomer -> {
            existingCustomer.setCustomerName(updatedCustomer.getCustomerName());
            existingCustomer.setCity(updatedCustomer.getCity());
            existingCustomer.setMobileNumber(updatedCustomer.getMobileNumber());
            return customerRepository.save(existingCustomer);
        });
    }

    public boolean deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}