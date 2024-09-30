package com.example.democust.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.democust.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
