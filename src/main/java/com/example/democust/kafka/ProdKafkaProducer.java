package com.example.democust.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import com.example.democust.model.Product;

@Service
public class ProdKafkaProducer {
	
	private static final Logger LOGGER =
	   		 LoggerFactory.getLogger(CustKafkaProducer.class);
	
	
	private KafkaTemplate<String, Product> kafkaTemplate;

	public ProdKafkaProducer(KafkaTemplate<String, Product> kafkaTemplate) {
		
		this.kafkaTemplate = kafkaTemplate;
	}
	
	
	public void sendMessage(Product data) {
		
		LOGGER.info(String.format("Message sent %s", data));
		Message<Product>  message=MessageBuilder
				.withPayload(data)
				.setHeader(KafkaHeaders.TOPIC, "java_json")
				.build();
		kafkaTemplate.send(message);
	}

}
