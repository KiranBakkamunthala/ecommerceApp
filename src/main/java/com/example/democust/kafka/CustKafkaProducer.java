package com.example.democust.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.example.democust.model.Customer;

@Service
public class CustKafkaProducer {
	
	private static final Logger LOGGER =
	   		 LoggerFactory.getLogger(CustKafkaProducer.class);
	
	
	private KafkaTemplate<String, Customer> kafkaTemplate;

	public CustKafkaProducer(KafkaTemplate<String, Customer> kafkaTemplate) {
		
		this.kafkaTemplate = kafkaTemplate;
	}
	
	
	public void sendMessage(Customer data) {
		
		LOGGER.info(String.format("Message sent %s", data));
		Message<Customer>  message=MessageBuilder
				.withPayload(data)
				.setHeader(KafkaHeaders.TOPIC, "javaguides_json")
				.build();
		kafkaTemplate.send(message);
	}
	

}
