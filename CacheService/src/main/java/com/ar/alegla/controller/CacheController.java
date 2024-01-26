package com.ar.alegla.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ar.alegla.domain.ResponseService;
import com.ar.alegla.environment.EnvironmentVariables;
import com.ar.alegla.service.ManagementFiles;
import com.ar.alegla.service.ServiceData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("cache")
public class CacheController {

	private static final Logger LOG = LogManager.getLogger(CacheController.class);
	
	@Autowired
	ObjectMapper objMapper;	
		
	@Autowired
	EnvironmentVariables envVariables;
	
	@Autowired
	ServiceData serviceData;
	
	@Autowired
	ManagementFiles managementFiles;
	
	@PostMapping(path="save", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> cacheSave(@RequestBody Object payload) throws JsonProcessingException {
		System.out.println(payload);
		JSONObject json = serviceData.putData(payload, envVariables.getCacheDirectory());
		
		return new ResponseEntity<Object>(objMapper.writeValueAsString(new ResponseService("201","OK", json.get("data"), "The payload was saved with the ID: " + "")), HttpStatus.OK);
	}
}
