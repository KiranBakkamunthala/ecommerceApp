package com.example.democust.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.democust.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}