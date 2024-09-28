package com.ar.alegla.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Response {

	@Autowired
	ObjectMapper objMapper;
	
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("statusCode")
	private String statusCode;
	
	@JsonProperty("data")
	private List<Object> data = new ArrayList<Object>();	

	
	public Response() {}

	public Response(String statusCode, Object data) {
		status = "OK";
		this.statusCode = statusCode;
		if(data != null) this.data.add(data);
		
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(Object data) {
		if(data != null) this.data.add(data);
	}

	public String getStatus() {
		return status;
	}
	
	
}
