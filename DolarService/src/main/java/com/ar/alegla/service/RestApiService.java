package com.ar.alegla.service;

import org.springframework.http.ResponseEntity;

import com.ar.alegla.models.DolarResponse;
import com.ar.alegla.models.DolarStatusResponse;

public interface RestApiService {

	ResponseEntity<DolarStatusResponse> statusService(); 
	
	ResponseEntity<DolarResponse[]> allTypesOfDolar();
	 
	ResponseEntity<DolarResponse> dolarOficial();
	
	ResponseEntity<DolarResponse> dolarBlue();
	
	ResponseEntity<DolarResponse> dolarBolsa();
	
	ResponseEntity<DolarResponse> dolarContadoConLiqui();
	
	ResponseEntity<DolarResponse> dolarTarjeta();
	
	ResponseEntity<DolarResponse> dolarMayorista();
	
	ResponseEntity<DolarResponse> dolarCripto();
	
	
}
