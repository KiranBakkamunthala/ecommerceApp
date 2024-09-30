package com.example.democust.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.democust.model.Customer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustKafkaConsumer {
	
		
	@KafkaListener(topics="javaguides_json",groupId = "myGroup")
	public void consume(Customer customer) {
		log.info(String.format("Message receive -> %s", customer));
	}

}
