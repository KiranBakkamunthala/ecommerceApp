package com.example.democust.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.democust.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
