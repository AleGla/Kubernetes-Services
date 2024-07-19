package com.alegla.FaceDetector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alegla.FaceDetector.detector.FaceDetector;
import com.alegla.FaceDetector.detector.FaceDetector180;
import com.alegla.FaceDetector.detector.FaceDetector90ClockWise;
import com.alegla.FaceDetector.detector.FaceDetector90CounterClockWise;
import com.alegla.FaceDetector.models.Request;

@RestController
public class Controller {
	
	@Autowired
	FaceDetector faceDetector1;
	
	@Autowired
	FaceDetector180 faceDetector2;
	
	@Autowired
	FaceDetector90ClockWise faceDetector3;
	
	@Autowired
	FaceDetector90CounterClockWise faceDetector4;

	@PostMapping(path="/face", produces= MediaType.APPLICATION_JSON_VALUE)
	public String testing(@RequestBody String base64) throws Exception {
		boolean faceValid = false;
		int attempt = 1;
		String image = null;
		while(!faceValid) {
			image = faceDetector1.getFaceDetector(new Request(base64, "1", "DNI", attempt));
			if(image.equals("FAIL")) {
				image = faceDetector2.getFaceDetector180(new Request(base64, "1", "DNI", attempt));
				if(image.equals("FAIL")) {
					image = faceDetector3.getFaceDetector90ClockWise(new Request(base64, "1", "DNI", attempt));
					if(image.equals("FAIL")) {
						image = faceDetector4.getFaceDetector90CounterClockWise(new Request(base64, "1", "DNI", attempt));
						if(!image.equals("FAIL")) {
							faceValid = true; 
							break;
						}
					}else {
						faceValid = true; 
						break;
					}
				}else {
					faceValid = true; 
					break;
				}
			}else {
				faceValid = true; 
				break;
			}				
				
			attempt++;
			if(attempt == 5) break;
		}
		return image;		
	}
	
	
	
	
}
