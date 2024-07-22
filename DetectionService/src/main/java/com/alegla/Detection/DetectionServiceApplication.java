package com.alegla.Detection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DetectionServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DetectionServiceApplication.class, args);
	}

	//Step executing
	/*
	 1- Use FaceDetector class
	 2- user FaceDetector90ClockWise class
	 3. Use FaceDetectorCounterClockWise class
	 4- Use FaceDetector180 class
	 //If the face was croped well, end execution
	  END.
	  
	   If don't
	 5- Update attempt number 1 to 2 and execute again the steps 1 to 4
	 6- You should that executions for 4 attempts (inclusive)
	 
	   
	   In the last attempts, if found de face you response with the cropped
	   and otherwise inform the user that not found the face in the image.
	   
	   
	   Last Thins is add the scheduler to delete image storaged for hour.
	  *
	 */
}
