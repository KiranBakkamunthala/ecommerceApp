package com.example.democust.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.democust.exception.ResourceNotFoundException;
import com.example.democust.model.Customer;
import com.example.democust.repository.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
 
    @Cacheable(value = "customers", key = "#id")
    public Customer getCustomerById(Long id) {
    	logger.info("Fetching customer with ID: {}", id);
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
    }

   @CachePut(value = "customers", key = "#customer.id")
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

   
    public Customer updateCustomer(Long id, Customer customer) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setFirstName(customer.getFirstName());
                    existingCustomer.setLastName(customer.getLastName());
                    return customerRepository.save(existingCustomer);
                }).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }

   @CacheEvict(value = "customers", key = "#id")
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
