package com.ar.alegla.exceptions;

public class CustomException{
	
	private String status;
	
	private String message;

	private int statusCode;	

	private String errorCode;
	
	
	public CustomException() {}

	public CustomException(String message, int statusCode, String errorCode) {
		status = "ERROR";
		this.message = message;
		this.statusCode = statusCode;
		this.errorCode = errorCode;		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getStatus() {
		return status;
	}
	


}
