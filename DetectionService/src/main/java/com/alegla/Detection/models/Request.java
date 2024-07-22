package com.alegla.Detection.models;

public class Request {

	private String image;
	private String facesToDetect;
	private String imageType;
	private int attempt;
	
	public Request(){}
	public Request(String image, String facesToDetect, String imageType, int attempt) {
		super();
		this.image = image;
		this.facesToDetect = facesToDetect;
		this.imageType = imageType;
		this.attempt = attempt;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getFacesToDetect() {
		return facesToDetect;
	}
	public void setFacesToDetect(String facesToDetect) {
		this.facesToDetect = facesToDetect;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public int getAttempt() {
		return attempt;
	}
	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}
	
	
	
	
}
