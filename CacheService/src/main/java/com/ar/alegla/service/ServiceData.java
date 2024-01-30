package com.ar.alegla.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.ar.alegla.domain.AdditionalValues;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

@Service
public class ServiceData {

	private static final Logger log = LoggerFactory.getLogger(ServiceData.class);
		
	
	public JSONObject createData(Object payload, String directory) {
		String id = generateId(directory);		
		long createdTime = System.currentTimeMillis();
		JSONObject json = payloadTransformJson(payload);
		json.put("additionalValues", new JSONObject(new AdditionalValues(id, createdTime)));	
		saveData(directory, id, json.toString());
		log.info("ID: " + id + " | Executing POST method, data was storage with the filename:" + id.concat(".json") + " | PAYLOAD: " + payload);
		return json;
	}
	
	public JSONObject putData(String id, Object payload, String directory) {
		JSONObject json = payloadTransformJson(payload);
		long createdTime = System.currentTimeMillis();
		json.put("additionalValues", new JSONObject(new AdditionalValues(id, createdTime)));
		if(ManagementFiles.isFileExists(directory, id)) {
			saveData(directory, id, json.toString());
			log.info("ID: " + id + " | Executing PUT method, was updated successfully - OK");
			return json;
		}else {
			log.info("ID: " + id + " | Executing PUT method, not found data stored for the ID: " + id);
			return null;
		}
		
	}
	
	public JSONObject getData(String id, String directory) throws StreamReadException, DatabindException, IOException {
		String pathFile = directory + id + ".json";
		JSONObject payload = new JSONObject(Files.readString(Paths.get(pathFile), StandardCharsets.UTF_8));
		log.info("ID: " + id + " | Executing GET method, data recovered:" + payload);
		return payload.getJSONObject("data");
	}
	
	private void saveData(String directory, String id, String json) {
		ManagementFiles.writeFile(directory, id, json);
	}
	
	private String generateId(String directory) {	
		boolean isExists = true;
		String id = null;
		while(isExists) {
			 id = UUID.randomUUID().toString();
			isExists = ManagementFiles.isFileExists(directory, id.concat(".json"));
		}
		return id;
	}
	
	private JSONObject payloadTransformJson(Object payload) {
		JSONObject json = new JSONObject();
		json.put("data", payload);
		return json;
	}
	
	
}
