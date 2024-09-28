package com.ar.alegla.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import sun.security.provider.certpath.SunCertPathBuilderException;

@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@Autowired
	ObjectMapper objMapper;
	
    // Handle RuntimeException
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> Exception(RuntimeException ex) throws JsonProcessingException {	
    	log.error("ERROR - RuntimeException Type: ".concat(ex.getMessage()), ex);
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	return new ResponseEntity<>(objMapper.writeValueAsString(new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "SV001")), headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
    // Handle InternalServerError
    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<String> handleResourceNotFoundException(InternalServerError ex) throws JsonProcessingException {	
    	log.error("ERROR - InternalServerError Type: ".concat(ex.getMessage()), ex);
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	return new ResponseEntity<>(objMapper.writeValueAsString(new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "SV002")), headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    // Handle Bad Request
    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<String> handleBadRequest(BadRequest ex) throws JsonProcessingException {	
    	log.error("ERROR - BadRequest Type: ".concat(ex.getMessage()), ex);
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	return new ResponseEntity<>(objMapper.writeValueAsString(new CustomException(HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value(), "SV003")), headers, HttpStatus.BAD_REQUEST);
    }
    
    // Handle METHOD_NOT_ALLOWED
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) throws JsonProcessingException {	
    	log.error("ERROR - HttpRequestMethodNotSupportedException Type: ".concat(ex.getMessage()), ex);
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	return new ResponseEntity<>(objMapper.writeValueAsString(new CustomException(HttpStatus.METHOD_NOT_ALLOWED.name(), HttpStatus.METHOD_NOT_ALLOWED.value(), "SV004")), headers, HttpStatus.METHOD_NOT_ALLOWED);
    }
    
    
    // Handle Resource Not Found 
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> handleResourceMethodNotAllowed(NoHandlerFoundException ex) throws JsonProcessingException {
    	log.error("ERROR - NoHandlerFoundException Type: ".concat(ex.getMessage()), ex);
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	return new ResponseEntity<>(objMapper.writeValueAsString(new CustomException(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(), "SV005")), headers, HttpStatus.NOT_FOUND); 
    }

    // Handle HttpServerErrorException 
    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<String> handleHttpServerErrorException(HttpServerErrorException ex) throws JsonProcessingException {	
    	log.error("ERROR - HttpServerErrorException Type: ".concat(ex.getMessage()), ex);
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	return new ResponseEntity<>(objMapper.writeValueAsString(new CustomException(ex.getStatusText(), ex.getStatusCode().value(), "SV-RT".concat(String.valueOf(ex.getStatusCode().value())))), headers, ex.getStatusCode().value());
    }
    
    // Handle HttpClientErrorException
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException ex) throws JsonProcessingException {	
    	log.error("ERROR - HttpClientErrorException Type: ".concat(ex.getMessage()), ex);
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	return new ResponseEntity<>(objMapper.writeValueAsString(new CustomException(ex.getStatusText(), ex.getStatusCode().value(), "SV-RT".concat(String.valueOf(ex.getStatusCode().value())))), headers, ex.getStatusCode().value());
    }
    
    
    // Handle SunCertPathBuilderException
    @ExceptionHandler(SunCertPathBuilderException.class)
    public ResponseEntity<String> handleRSunCertPathBuilderException(SunCertPathBuilderException ex) throws JsonProcessingException {
    	log.error("ERROR - SunCertPathBuilderException Type: ".concat(ex.getMessage()), ex);
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	return new ResponseEntity<>(objMapper.writeValueAsString(new CustomException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "SV-RT".concat(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value())))), headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    

   

}
