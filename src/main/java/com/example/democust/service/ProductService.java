package com.example.democust.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.democust.exception.ResourceNotFoundException;
import com.example.democust.model.Product;
import com.example.democust.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	/* @CachePut(value = "products", key = "#product.id") */
	public Product addProduct(Product product) {
		log.info("Save data in Database :");
		return productRepository.save(product);
	}

	
	@Cacheable(value = "products", key = "#id") 
	public Product getProductById(Long id) {
		log.info("Retrive the data from DB, product with ID:{}", id);
		return productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
	}

	public Product updateProduct(Long id, Product product) {
		return productRepository.findById(id).map(existingProduct -> {
			existingProduct.setBookTitle(product.getBookTitle());
			return productRepository.save(existingProduct);
		}).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
	}

	/* @CacheEvict(value = "products", key = "#id") */
	public void deleteProduct(Long id) {
		 log.info("deleted from Database, product with ID: {}", id);
		productRepository.deleteById(id);
	}
}
