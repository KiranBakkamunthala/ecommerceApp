package com.example.democust.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.democust.model.Customer;

@Service
public class CustKafkaConsumer {
	
	private static final Logger LOGGER =
	   		 LoggerFactory.getLogger(CustKafkaConsumer.class);
	
	@KafkaListener(topics="javaguides_json",groupId = "myGroup")
	public void consume(Customer customer) {
		LOGGER.info(String.format("Message receive -> %s", customer));
	}

}
