package com.example.democust.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.democust.kafka.ProdKafkaProducer;
import com.example.democust.model.Product;
import com.example.democust.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private ProdKafkaProducer prodKafkaProducer;

	@Autowired
	public ProductController(ProdKafkaProducer prodKafkaProducer) {
		this.prodKafkaProducer = prodKafkaProducer;
	}

	@PostMapping("/productinfo")
	public ResponseEntity<String> productInfo(@RequestBody Product product) {
		prodKafkaProducer.sendMessage(product);
		return ResponseEntity.ok("JSON message sent to kafka topic");
	}

	@Autowired
	private ProductService productService;

	@GetMapping
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		Product product = productService.getProductById(id);
		return ResponseEntity.ok().body(product);
	}

	@PostMapping
	public Product addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}

	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
		return productService.updateProduct(id, product);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
}
