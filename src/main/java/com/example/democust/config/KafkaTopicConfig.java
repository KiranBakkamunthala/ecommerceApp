package com.example.democust.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
	
	@Bean
	public NewTopic javaguidesJsonTopic() {
		return TopicBuilder.name("javaguides_json").build();
	}
	
	@Bean
	public NewTopic javaJsonTopic() {
		return TopicBuilder.name("java_json").build();
	}

}
