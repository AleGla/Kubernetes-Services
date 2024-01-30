package com.ar.alegla.controller;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PostMapping(path="save", consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> cacheSave(@RequestBody Object payload) throws JsonProcessingException {
		LOG.info("ID: xxxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx" + " | Executing POST method, data to storage:" + payload);
		JSONObject json = serviceData.createData(payload, envVariables.getCacheDirectory());
		return new ResponseEntity<Object>(objMapper.writeValueAsString(new ResponseService(json.getJSONObject("additionalValues").getString("id"), json.get("data"))), HttpStatus.OK);
	}
	
	@PutMapping(path="update/{id}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> cacheUpdate(@PathVariable String id, @RequestBody Object payload) throws JsonProcessingException, JSONException{
		LOG.info("ID: " + id + " | Executing PUT method, data to update:" + payload);
		JSONObject json = serviceData.putData(id, payload, envVariables.getCacheDirectory());
		if(json != null) return new ResponseEntity<Object>(objMapper.writeValueAsString(new ResponseService(id ,json.get("data"))), HttpStatus.OK);
		else return new ResponseEntity<Object>(objMapper.writeValueAsString(new ResponseService(id, null)), HttpStatus.NO_CONTENT);
		
	}
	
	@GetMapping(path="get/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> cacheUpdate(@PathVariable String id) throws JSONException, IOException{
		if(ManagementFiles.isFileExists(envVariables.getCacheDirectory(), id)) {		
			LOG.info("ID: " + id + " | Start GET method to get data storaged");
			JSONObject json = serviceData.getData(id,envVariables.getCacheDirectory());
			LOG.info("ID: " + id + " | END GET method, response json:" + json);
		
			return new ResponseEntity<Object>(objMapper.writeValueAsString(new ResponseService(id, json.toMap())), HttpStatus.OK);
	    }else {
	    	return new ResponseEntity<Object>(objMapper.writeValueAsString(new ResponseService(id ,null)), HttpStatus.NO_CONTENT);
	    }
	}	
	
	@Scheduled(cron="${scheduledDeleteOldData}")
	public void deleteOldData() {
		LOG.info("Scheduled Method to delete the data storaged is executing" );
		File folder = new File(envVariables.getCacheDirectory());
		File[] listOfFiles = folder.listFiles();
		for(File file: listOfFiles) {
			if(file.isFile()) {
				Long lastModified = file.lastModified();
				long timeDiference = System.currentTimeMillis() - lastModified;
				if(timeDiference >= envVariables.getLifeTimeOfData()) {
					file.delete();
					LOG.info("The File: " + file.getName() + " was deleted");
				}
			}
		}
		LOG.info("END execution of the Scheduled Method");		
	}
}
