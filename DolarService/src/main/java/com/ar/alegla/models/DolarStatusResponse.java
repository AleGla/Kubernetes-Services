package com.ar.alegla.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class DolarStatusResponse {

	@JsonProperty("estado")
	private String estado;
	
	@JsonProperty("aleatorio")
	private int aleatorio;
	
	public DolarStatusResponse() {}

	public DolarStatusResponse(String estado, int aleatorio) {
		this.estado = estado;
		this.aleatorio = aleatorio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getAleatorio() {
		return aleatorio;
	}

	public void setAleatorio(int aleatorio) {
		this.aleatorio = aleatorio;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DolarStatusResponse [estado=");
		builder.append(estado);
		builder.append(", aleatorio=");
		builder.append(aleatorio);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
