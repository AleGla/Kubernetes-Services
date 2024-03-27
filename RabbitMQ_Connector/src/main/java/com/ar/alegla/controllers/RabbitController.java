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
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
	public ResponseEntity<String> getLogs(@RequestHeader(value = "queueName") String queueName, @RequestHeader(value = "quantityMessages") int quantityMessages, @RequestHeader(value = "id", required = false) String id) throws JsonMappingException, JsonProcessingException{
		LOG.info("----> EXECUTE START | Endpoint - Consume | ID: " + id + " , Queue Name: " + queueName + " , Quantity Messages requested : " + quantityMessages + " <----");
		List<String> log = queueConsumer.processMessage(queueName, quantityMessages, id);
		if(!log.isEmpty()) {
			LOG.info("----> EXECUTE END | Endpoint - Consume | ID: " + id + ", OK <----");
			return new ResponseEntity<String>(log.toString(), HttpStatus.OK);
		}
		LOG.info("----> EXECUTE END | Endpoint - Consume | ID: " + id + ", The topic doesn't have message <----");
		return new ResponseEntity<String>("The topic doesn't have message", HttpStatus.NO_CONTENT);
		
	}
	
	@PostMapping("publish")
	public ResponseEntity<String> storeLog(@RequestBody Map<String, Object> payload, @RequestHeader(value = "exchange") String exchange, @RequestHeader(value = "routingKey") String routingKey, @RequestHeader(value = "id", required = false) String id) throws JsonProcessingException, AmqpException{
		LOG.info("----> EXECUTE START | Endpoint - Publish | ID: " + id + " , Exchange: " + exchange + " , RoutingKey : " + routingKey + " <----");
		JSONObject json = new JSONObject();
		json.put("data", new JSONObject(payload));
		queueProducer.produce(json.toString(), exchange, routingKey, id);
		LOG.info("----> EXECUTE END | Endpoint - Publish | ID: " + id + " , OK <----");
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}
	
	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<String> generalException(MissingRequestHeaderException ex){
		LOG.info("----> EXECUTE END OK <----");
		return new ResponseEntity<String>(ex.getBody().toString(), HttpStatus.BAD_REQUEST);
	}
	
}
