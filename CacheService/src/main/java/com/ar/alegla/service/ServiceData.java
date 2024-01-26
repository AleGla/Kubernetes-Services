package com.ar.alegla.service;

import java.util.ArrayList;
import java.util.UUID;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.ar.alegla.domain.AdditionalValues;

@Service
public class ServiceData {

	private static final Logger log = LoggerFactory.getLogger(ServiceData.class);
		
	
	public JSONObject putData(Object payload, String directory) {
		String id = generateId(directory);		
		long createdTime = System.currentTimeMillis();
		JSONObject json = new JSONObject();
		json.put("data", payload);
		json.put("additionalValues", new JSONObject(new AdditionalValues(id, createdTime)));
				
		saveData(directory, id, json.toString());
		return json;
	}
	
	private void saveData(String directory, String id, String json) {
		System.out.println(json.toString());
		ManagementFiles.writeFile(directory, id, json);
		//return true;
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
}
