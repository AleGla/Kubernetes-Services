package com.ar.alegla.components;

import java.util.ArrayList;
import java.util.List;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Component
public class QueueConsumer {

	private final RabbitTemplate rabbitTemplate;
	
	@Autowired
	public QueueConsumer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	
	private String receiveMessage(String queueName) {		
		return (String)rabbitTemplate.receiveAndConvert(queueName);	
	}
	
	public List<String> processMessage(String queueName, int quantityMessages) throws JsonMappingException, JsonProcessingException {
		List<String> rabbitMessages = new ArrayList<String>();
		String message = receiveMessage(queueName);
		while(message != null && rabbitMessages.size() < quantityMessages) {
			rabbitMessages.add(message);
			message = receiveMessage(queueName);
			System.out.println("Ejecutado-->");
		}
		return rabbitMessages;
	}

	
}
