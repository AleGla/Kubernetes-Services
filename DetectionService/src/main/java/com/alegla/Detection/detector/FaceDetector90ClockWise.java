package com.alegla.Detection.detector;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.opencv.core.Core;
import org.opencv.core.CvException;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alegla.Detection.models.Image;
import com.alegla.Detection.models.Request;
import com.alegla.Detection.tools.ToolsDetection;

@Component
public class FaceDetector90ClockWise {
	
	private static final Logger LOG = LogManager.getLogger(FaceDetector90ClockWise.class);
	
	@Value("${pathLib}")
	private String pathLib;
	
	@Value("${principalMeshFile}")
	private String principalMeshFile;
	
	@Value("${validationMeshFile}")
	private String validationMeshFile;
	
	@Value("${altMeshFile}")
	private String altMeshFile;
	
	public String getFaceDetector90ClockWise(Request data) throws IOException {
	
		ToolsDetection.loadLibrary(pathLib);
		
		Mat src = Imgcodecs.imdecode(new MatOfByte(ToolsDetection.decodeBase64(data.getImage()).toByteArray()), Imgcodecs.IMREAD_UNCHANGED);
		
		//Is it the first attempt?
		LOG.info("Attempt number: " + data.getAttempt());
		Mat resizeImage = ToolsDetection.resizeImage(src, data.getAttempt(), data.getImageType());
		
		
		//Rotation 90° ClockWise
		Mat dst = new Mat();
		ToolsDetection.rotateImage(resizeImage, dst, Core.ROTATE_90_CLOCKWISE);
		
		//Save image
		//ToolsDetection.saveImage(id, src, resizeImage, null, "original");
		
		//Detecting the face
		MatOfRect faceDetection = new MatOfRect();
		CascadeClassifier classifier = new CascadeClassifier(data.getAttempt() <= 2 ? principalMeshFile : altMeshFile);
		classifier.detectMultiScale(resizeImage, faceDetection);
		List<Image> facesDetected = new ArrayList<Image>();
		
		if(faceDetection.toArray().length == 0) {
			return "FAIL";		
		
		}else {
			Rect rectCrop = null;
			for (Rect rect: faceDetection.toArray()) {
				//Cropping face
				if(data.getImageType().equals("DNI")) {
					if(data.getAttempt() <= 2) rectCrop = new Rect(rect.x - 70, rect.y -90, 320, 400);
					else rectCrop = data.getAttempt() == 3 ? new Rect(rect.x + 5, rect.y - 12, rect.width + 25, rect.height +30) : new Rect (rect.x, rect.y, rect.width, rect.height);
					
				}else {
					rectCrop = new Rect(rect.x, rect.y, rect.width, rect.height);
				}
				
				Mat imgRect = null;
				try {
					//Writing the image
					imgRect = new Mat(resizeImage, rectCrop);
				}catch(CvException ex) {
					try {
						new Rect(rect.x + 5, rect.y - 12, rect.width + 25, rect.height + 30);
						
						//Writing the image
						imgRect = new Mat(resizeImage, rectCrop);
					
					}catch(CvException exc) {
						rectCrop = new Rect(rect.x, rect.y, rect.width, rect.height);
						
						//Writing the image
						imgRect = new Mat(resizeImage, rectCrop);
					}
				}
				facesDetected.add(new Image(ToolsDetection.encodeBase64(imgRect, "verificationId")));
			}
			List<Image> checkingDetection = ToolsDetection.checkFaceObteined(facesDetected, classifier, Integer.parseInt(data.getFacesToDetect()), data.getAttempt() <= 2 ? validationMeshFile : altMeshFile, data.getImageType());
			
			if(checkingDetection == null) {
				return "FAIL";
			}else {
				//ToolsDetection.saveImage("verificationId", checkingDetection.get(0).getFile(), isSaved, maxImagesStorage, pathImages);
				return new JSONArray(checkingDetection).toString();
			}
		}
		
	}
		
	public String getPathLib() {
		return pathLib;
	}
	public void setPathLib(String pathLib) {
		this.pathLib = pathLib;
	}
	public String getAltMeshFile() {
		return altMeshFile;
	}
	public void setAltMeshFile(String altMeshFile) {
		this.altMeshFile = altMeshFile;
	}
	public String getPrincipalMeshFile() {
		return principalMeshFile;
	}
	public void setPrincipalMeshFile(String principalMeshFile) {
		this.principalMeshFile = principalMeshFile;
	}
	public String getValidationMeshFile() {
		return validationMeshFile;
	}
	public void setValidationMeshFile(String validationMeshFile) {
		this.validationMeshFile = validationMeshFile;
	}
	
	
	
	
	
	
}
