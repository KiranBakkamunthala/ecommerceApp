package com.example.democust.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.democust.model.Customer;
import com.example.democust.repository.CustomerRepository;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetCustomerById() {
        // Arrange
        Customer customer = new Customer();       
        customer.setFirstName("John");
        customer.setLastName("Doe");

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        // Act
        Customer result = customerService.getCustomerById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
    }
}

