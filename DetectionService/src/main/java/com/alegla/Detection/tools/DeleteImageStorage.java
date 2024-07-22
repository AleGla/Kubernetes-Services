package com.alegla.Detection.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;


public class DeleteImageStorage {

	private static final Logger LOG = LogManager.getLogger(DeleteImageStorage.class);
	
	@Value("${pathImages}")
	private String pathImages;
	
	@Value("${pathZip}")
	private String pathZip;
	
	private List<String> filesListInDir = new ArrayList<String>();
	
	// deleteZip(3L, ".zip", pathZip);
	//createZip(pathZip, pathImages);
	//deleteImages(pathImages);
	
	
	private void deleteImages(String path) {
		LOG.info("Step 3 of 3 || Delete - Images storaged: STATUS -> PROCESSING...");
		try {
			FileUtils.cleanDirectory(new File(path));
			LOG.info("Has been removed the images successfully");
		}catch(IOException e) {
			LOG.warn("There was an error trying to delete the images storaged" + e.getStackTrace());
		}finally {
			LOG.info("STEP 3 of 3 || Delete - Images storaged: STATUS -> READY");
		}
	}
	
	private void createZip(String pathZip, String pathImages, List<String> filesListInDir) {
		LOG.info("SETIP 2 of 3 || Creating - ZIP File: STATUS -> PROCESSING");
		try {
			if(new File(pathImages).exists()) {
				populateFilesList(new File(pathImages));
				if(filesListInDir.size() > 0) {
					if(!new File(pathZip).exists()) {
						LOG.info("Creating the folder where to save ZIP file, 'cause It doesn't exists");
						new File(pathZip).mkdir();
						LOG.info("The folder was created successfully -> " + pathZip);
					}
				FileOutputStream fos = new FileOutputStream(pathZip.concat("FacesImages.").concat(new SimpleDateFormat("yyyy-MM-dd").format(new Date())).concat(".zip"));
				ZipOutputStream zos = new ZipOutputStream(fos);
				for(String filePath: filesListInDir) {
					ZipEntry ze = new ZipEntry(filePath.substring(new File(pathImages).getAbsolutePath().length() + 1, filePath.length()));
					zos.putNextEntry(ze);
					
					FileInputStream fis = new FileInputStream(filePath);
					byte[] buffer = new byte[1024];
					int len;
					while((len = fis.read(buffer)) > 0) {
						zos.write(buffer, 0, len);
					}
					zos.closeEntry();
					fis.close();
				}
				zos.close();
				fos.close();
				LOG.info("The ZIp file was created successfully and were compressed " + filesListInDir.size() + " files");
				}else {
					LOG.info("The images folder is empty and won't create ZIP file");
				}
			}else {
				LOG.info("The folder doesn't exists -> " + pathImages);
			}
			
		}catch(IOException e) {
			LOG.info("Has been an error to create ZIP file");
		}finally {
			LOG.info("STEP 2 of 3 || Creation - ZIP file: STATUS -> READY");
		}
	}
	
	private void populateFilesList(File dir) {
		File[] files = dir.listFiles();
		for(File file: files) {
			if(file.isFile())
				filesListInDir.add(file.getAbsolutePath());
			else
				populateFilesList(file);
		}
	}
	
	public void deleteZip(long nDays, String extension, String pathZip) {
		LOG.info("STEP 1 of 3 || Check - Old ZIP files (3 days ago) to delete: STATUS -> PROCESSING...");
		int zipDeleted = 0;
		try {
			if(new File(pathZip).exists()) {
				File[] listAllFiles = new File(pathZip).listFiles();
				
				long deletion = System.currentTimeMillis() - (nDays * 24 *60 * 60 * 1000L);
				
				for(File listFiles: listAllFiles) {
					if(listFiles.getName().endsWith(extension) && listFiles.lastModified() < deletion) {
						if(listFiles.delete()) {
							zipDeleted++;
							LOG.info("Has been deleted the ZIp file -> " + listFiles.getName());
						}
					}
				}
				LOG.info("has been deleted: " + zipDeleted + " ZIP files");
			}else {
				LOG.info("The folder's path doesn't exists");
			}
		}catch(Exception ex) {
			LOG.warn("Error - " + ex.getStackTrace());
		}finally {
			LOG.info("STEP 1 of 3 || Check - Old ZIP files (3 days ago) to delete: STATUS -> READY");
		}
	}
		
}
