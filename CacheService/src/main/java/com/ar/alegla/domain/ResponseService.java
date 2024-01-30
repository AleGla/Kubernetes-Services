package com.ar.alegla.domain;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseService {
	
	@Autowired
	ObjectMapper objMapper;
	
	private String id;
	private Object data;
	
	
	public ResponseService(String id, Object data) {
		super();		
		this.id = id;
		this.data = data;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) throws JsonProcessingException {
		this.data = data;
	}
		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "{'id':" + id + ", 'data':" + data + "}";
	}
	
	
	
}
