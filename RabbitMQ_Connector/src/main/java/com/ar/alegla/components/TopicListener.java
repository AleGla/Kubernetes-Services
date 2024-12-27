package com.ar.alegla.components;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicListener {

	@RabbitListener(queues = {"local.queue.k8s.2","local.queue.k8s.1"})
	public void receivedBulkMessage(String message) {
		System.out.println("Received Messages: " + message);
	}
}
