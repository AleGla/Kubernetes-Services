package com.ar.alegla.domain;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseService {
	
	@Autowired
	ObjectMapper objMapper;
	
	private String code;
	private String status;
	private Object data;
	private String description;
	
	public ResponseService(String code, String status, Object data, String description) throws JsonProcessingException {
		super();
		this.code = code;
		this.status = status;
		this.data = data;
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) throws JsonProcessingException {
		this.data = data;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "{'code':" + code + ", 'status':" + status + ", 'data':" + data + ", 'description':"
				+ description + "}";
	}
	
	
	
}
