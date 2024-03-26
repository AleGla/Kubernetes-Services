package com.ar.alegla.controllers;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ar.alegla.components.QueueConsumer;
import com.ar.alegla.components.QueueProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("RabbitMQ")
public class RabbitController {

	private static final Logger LOG = LogManager.getLogger(RabbitController.class);
	
	@Autowired
	private QueueConsumer queueConsumer;
	
	@Autowired
	private QueueProducer queueProducer;
	
	@GetMapping(path="consume", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getLogs(@RequestHeader String queueName, @RequestHeader int quantityMessages) throws JsonMappingException, JsonProcessingException{
		List<String> log = queueConsumer.processMessage(queueName, quantityMessages);
		if(!log.isEmpty()) {
			return new ResponseEntity<String>(log.toString(), HttpStatus.OK);
		}
		return new ResponseEntity<String>("The topic doesn't have message", HttpStatus.NO_CONTENT);
		
	}
	
	@PostMapping("publish")
	public ResponseEntity<String> storeLog(@RequestBody Map<String, Object> payload, @RequestHeader String exchange, @RequestHeader String routingKey) throws JsonProcessingException, AmqpException{
		JSONObject json = new JSONObject();
		json.put("data", new JSONObject(payload));
		queueProducer.produce(json.toString(), exchange, routingKey);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}
	
}
