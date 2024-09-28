package com.ar.alegla.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class DolarResponse implements Serializable{

	private static final long serialVersionUID = 6127292546150138186L;

	@JsonProperty("compra")
	private double buy_price;
	
	@JsonProperty("venta")
	private double sell_price;
	
	@JsonProperty("casa")
	private String house;
	
	@JsonProperty("nombre")
	private String name;
	
	@JsonProperty("moneda")
	private String currency;
	
	@JsonProperty("fechaActualizacion")
	private String lastUpdated;
	
	public DolarResponse() {}

	public DolarResponse(double buy_price, double sell_price, String house, String name, String currency, String lastUpdated) {
		this.buy_price = buy_price;
		this.sell_price = sell_price;
		this.house = house;
		this.name = name;
		this.currency = currency;
		this.lastUpdated = lastUpdated;
	}

	public double getBuy_price() {
		return buy_price;
	}

	public void setBuy_price(double buy_price) {
		this.buy_price = buy_price;
	}

	public double getSell_price() {
		return sell_price;
	}

	public void setSell_price(double sell_price) {
		this.sell_price = sell_price;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DolarResponse [buy_price=");
		builder.append(buy_price);
		builder.append(", sell_price=");
		builder.append(sell_price);
		builder.append(", house=");
		builder.append(house);
		builder.append(", name=");
		builder.append(name);
		builder.append(", currency=");
		builder.append(currency);
		builder.append(", lastUpdated=");
		builder.append(lastUpdated);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
