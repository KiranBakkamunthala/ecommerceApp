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
import com.example.democust.kafka.CustKafkaProducer;
import com.example.democust.model.Customer;
import com.example.democust.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	private CustKafkaProducer custKafProducer;

	@Autowired
	public CustomerController(CustKafkaProducer custKafProducer) {
		this.custKafProducer = custKafProducer;
	}

	@PostMapping("/custinfo")
	public ResponseEntity<String> publish(@RequestBody Customer customer) {
		custKafProducer.sendMessage(customer);
		return ResponseEntity.ok("JSON message sent to kafka topic");
	}

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
		Customer customer = customerService.getCustomerById(id);
		return ResponseEntity.ok().body(customer);
	}

	@PostMapping
	public Customer addCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}

	@PutMapping("/{id}")
	public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
		return customerService.updateCustomer(id, customer);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
		return ResponseEntity.noContent().build();
	}
}
