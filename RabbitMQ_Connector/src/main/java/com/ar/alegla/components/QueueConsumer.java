package com.ar.alegla.components;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ar.alegla.controllers.RabbitController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Component
public class QueueConsumer {

	private static final Logger LOG = LogManager.getLogger(QueueConsumer.class);
	
	private final RabbitTemplate rabbitTemplate;
	
	@Autowired
	public QueueConsumer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	
	private String receiveMessage(String queueName) {		
		return (String)rabbitTemplate.receiveAndConvert(queueName);	
	}
	
	public List<String> processMessage(String queueName, int quantityMessages, String id) throws JsonMappingException, JsonProcessingException {
		List<String> rabbitMessages = new ArrayList<String>();
		String message = receiveMessage(queueName);
		while(message != null && rabbitMessages.size() < quantityMessages) {
			rabbitMessages.add(message);
			message = receiveMessage(queueName);
		}
		LOG.info("----> EXECUTE IN PROGRESS | Endpoint - Consume | ID: " + id + " , Messages recovered " + rabbitMessages.size() + " <----");
		return rabbitMessages;
	}

	
}
