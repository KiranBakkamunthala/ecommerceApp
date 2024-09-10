package com.example.democust.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.democust.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}