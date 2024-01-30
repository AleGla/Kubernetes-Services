package com.ar.alegla.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class ManagementFiles {
	
	private static final Logger LOG = LogManager.getLogger(ManagementFiles.class);
	
   public static boolean writeFile(String directory, String fileName, String payload) {
			try {
				FileWriter writer = new FileWriter(directory + fileName.concat(".json"));
				writer.write(payload);
				writer.close();
				LOG.info("ID: " + fileName + " | The file was update successfully and saved" + " | PAYLOAD: " + payload);
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		return false;
	}
	
	public static boolean isFileExists(String directory, String fileName) {
		File file = new File(directory + fileName.concat(".json"));
		if(file.exists()) {
			LOG.info("ID: " + file.getName().replace(".json", "")  + " | The file already exists");
			return true;
		} else {
			LOG.info("ID: " + file.getName().replace(".json", "") + " | The file doesn't exists");
			return false;
		}		
	}

	
	
}
