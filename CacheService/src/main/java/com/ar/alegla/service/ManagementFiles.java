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
	
	private static boolean createFile(String directory, String fileName) {
		File file = new File(directory + fileName);
		try {
			if(file.createNewFile()) {
				LOG.info("File created successfully");
			}else {
				LOG.info("The file already exists");
			}
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean writeFile(String directory, String fileName, String payload) {
		if(createFile(directory, fileName.concat(".json"))) {		
			try {
				FileWriter writer = new FileWriter(directory + fileName.concat(".json"));
				writer.write(payload);
				writer.close();
				LOG.info("The file was wrote successfully and saved");
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		return false;
	}
	
	public static boolean isFileExists(String directory, String fileName) {
		File file = new File(directory + fileName.concat(".json"));
		if(file.exists()) {
			LOG.info("The file already exists");
			return true;
		} else {
			LOG.info("The file doesn't exists");
			return false;
		}		
	}

	
	
}
