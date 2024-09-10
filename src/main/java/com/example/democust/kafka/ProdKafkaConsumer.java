package com.example.democust.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.example.democust.model.Product;

@Service
public class ProdKafkaConsumer {
	private static final Logger LOGGER =
	   		 LoggerFactory.getLogger(ProdKafkaConsumer.class);
	
	@KafkaListener(topics="java_json",groupId = "myGroup")
	public void consume(Product product) {
		LOGGER.info(String.format("Message receive -> %s", product));
	}

}
