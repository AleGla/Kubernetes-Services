package com.ar.alegla.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class QueueProducer {

	private static final Logger LOG = LogManager.getLogger(QueueProducer.class);
	
	private final RabbitTemplate rabbitTemplate;
	
	@Autowired
	public QueueProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void produce(String message, String exchange, String routingKey, String id) throws JsonProcessingException, AmqpException {
		LOG.info("----> EXECUTE IN PROGRESS | Endpoint - Publish | ID: " + id + " , Preparing the Message to send <----");
		rabbitTemplate.convertAndSend(exchange, routingKey,message);
		LOG.info("----> EXECUTE IN PROGRESS | Endpoint - Publish | ID: " + id + " , The message was sent successfully <----");
	}
	
	
}
