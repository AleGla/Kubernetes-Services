package com.ar.alegla.components;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class QueueProducer {

	private final RabbitTemplate rabbitTemplate;
	
	@Autowired
	public QueueProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void produce(String message, String exchange, String routingKey) throws JsonProcessingException, AmqpException {
		rabbitTemplate.setExchange(exchange);
		rabbitTemplate.convertAndSend(exchange, routingKey,message);
	}
	
	
}
