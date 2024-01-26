package com.ar.alegla.domain;

public class AdditionalValues {

	private String id;
	private long createdTime;
	
	public AdditionalValues() {}

	public AdditionalValues(String id, long createdTime) {
		super();
		this.id = id;
		this.createdTime = createdTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}
	
	
	
}
