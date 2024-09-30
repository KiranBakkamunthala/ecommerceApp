package com.example.democust.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.example.democust.model.Product;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProdKafkaConsumer {
		
	@KafkaListener(topics="java_json",groupId = "myGroup")
	public void consume(Product product) {
		log.info(String.format("Message receive -> %s", product));
	}

}
