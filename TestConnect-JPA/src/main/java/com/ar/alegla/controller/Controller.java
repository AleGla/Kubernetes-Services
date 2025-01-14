package com.ar.alegla.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ar.alegla.models.Person;
import com.ar.alegla.service.PersonService;

@RestController
@RequestMapping("/person")
public class Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Controller.class);
	
	@Autowired
	private PersonService ps;

	@PostMapping(path = "/save", consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> newPerson(@RequestBody Person p) throws Exception {
			
		return new ResponseEntity<String>(ps.savePerson(p), HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Person>> findAllPersons() {
		return new ResponseEntity<List<Person>>(ps.getAllPerson(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/getByAgeRange", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Person>> getPersonByAgeRange(@RequestParam Integer minAge, @RequestParam Integer maxAge) throws Exception{
		
		System.out.println("minAge:"+ minAge + " | maxAge:" + maxAge);
		if(minAge == null || minAge <=0 || maxAge == null || maxAge <=0) {
			throw new Exception("Bad Request");		
		}		
		
		return new ResponseEntity<List<Person>>(ps.getPersonByAgeRange(minAge, maxAge), HttpStatus.OK);
	}
	
}
