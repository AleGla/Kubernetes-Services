package com.ar.alegla.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.ar.alegla.models.DolarResponse;
import com.ar.alegla.models.DolarStatusResponse;
import com.ar.alegla.utils.EnvConfig;

@Service
public class IRestApiService implements RestApiService{

	private static final Logger log = LoggerFactory.getLogger(IRestApiService.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EnvConfig constant;
	
	
	// APIREST - Request to get Status service
	public ResponseEntity<DolarStatusResponse> statusService() {
		
		final String url = constant.getUrlDomain() + constant.getUrlVersion() + constant.getUrlResourceStatus();
		
		try {
			log.info("Requesting for the status of the service");
						
			log.info("Request to Endpoint: " + url);
			
			ResponseEntity<DolarStatusResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, DolarStatusResponse.class);
			
			log.info("Response: " + response.toString());
			
			return response;			
		}catch(HttpServerErrorException ex) {
			log.error("Error calling to REST API: ".concat(url).concat(" | StatusCode: " + ex.getStatusText()));
			throw new HttpServerErrorException(ex.getStatusCode(), "Error calling to REST API: ".concat(constant.getUrlDomain() + constant.getUrlVersion()));
		}catch(HttpClientErrorException ex) {
			log.error("Error calling to REST API: ".concat(url).concat(" | StatusCode: " + ex.getStatusText()));
			throw new HttpClientErrorException(ex.getStatusCode(), "Error calling to REST API: ".concat(constant.getUrlDomain() + constant.getUrlVersion()));
		}
		
	}
	
	// APIREST - Request to get all types of dollar
	public ResponseEntity<DolarResponse[]> allTypesOfDolar() {
		
		final String url = constant.getUrlDomain() + constant.getUrlVersion() + constant.getUrlBasePath();
		
		try {
			log.info("Requesting for the all types of dollar");
						
			log.info("Request to Endpoint: " + url);
			
			ResponseEntity<DolarResponse[]> response = restTemplate.exchange(url, HttpMethod.GET, null, DolarResponse[].class);
			
			log.info("Status Code: " + response.getStatusCode());			
					
			return response;
		}catch(HttpServerErrorException ex) {
			log.error("Error calling to REST API: ".concat(url).concat(" | StatusCode: " + ex.getStatusText()));
			throw new HttpServerErrorException(ex.getStatusCode(), "Error calling to REST API: ".concat(constant.getUrlDomain() + constant.getUrlVersion()));
		}catch(HttpClientErrorException ex) {
			log.error("Error calling to REST API: ".concat(url).concat(" | StatusCode: " + ex.getStatusText()));
			throw new HttpClientErrorException(ex.getStatusCode(), "Error calling to REST API: ".concat(constant.getUrlDomain() + constant.getUrlVersion()));
		}
		
	}
	
	// APIREST - Request to get Dolar Oficial
	public ResponseEntity<DolarResponse> dolarOficial() {
		
		final String url = constant.getUrlDomain() + constant.getUrlVersion() + constant.getUrlBasePath() + constant.getUrlResourceDolarOficial();
		
		try {
			log.info("Requesting for Dolar oficial");			
			
			log.info("Request to Endpoint: " + url);
			
			ResponseEntity<DolarResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, DolarResponse.class);		
			
			log.info("Status Code: " + response.getStatusCode() + " | Response: " + response.getBody());
					
			return response;
		}catch(HttpServerErrorException ex) {
			log.error("Error calling to REST API: ".concat(url).concat(" | StatusCode: " + ex.getStatusText()));
			throw new HttpServerErrorException(ex.getStatusCode(), "Error calling to REST API: ".concat(constant.getUrlDomain() + constant.getUrlVersion()));
		}catch(HttpClientErrorException ex) {
			log.error("Error calling to REST API: ".concat(url).concat(" | StatusCode: " + ex.getStatusText()));
			throw new HttpClientErrorException(ex.getStatusCode(), "Error calling to REST API: ".concat(constant.getUrlDomain() + constant.getUrlVersion()));
		}
	
	}
	
	// APIREST - Request to get Dolar Blue
	public ResponseEntity<DolarResponse> dolarBlue() {
		
		final String url = constant.getUrlDomain() + constant.getUrlVersion() + constant.getUrlBasePath() + constant.getUrlResourceDolarBlue();
		
		try {
			log.info("Requesting for Dolar blue");			
			
			log.info("Request to Endpoint: " + url);
			
			ResponseEntity<DolarResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, DolarResponse.class);			
			
			log.info("Status Code: " + response.getStatusCode() + " | Response: " + response.getBody());
					
			return response;	
		}catch(HttpServerErrorException ex) {			
			log.error("Error calling to REST API: ".concat(url).concat(" | StatusCode: " + ex.getStatusText()));
			throw new HttpServerErrorException(ex.getStatusCode(), "Error calling to REST API: ".concat(constant.getUrlDomain() + constant.getUrlVersion()));
		}catch(HttpClientErrorException ex) {
			log.error("Error calling to REST API: ".concat(url).concat(" | StatusCode: " + ex.getStatusText()));
			throw new HttpClientErrorException(ex.getStatusCode(), "Error calling to REST API: ".concat(constant.getUrlDomain() + constant.getUrlVersion()));
		}
	
	}
	
	// APIREST - Request to get Dolar Bolsa
	public ResponseEntity<DolarResponse> dolarBolsa() {
		
		final String url = constant.getUrlDomain() + constant.getUrlVersion() + constant.getUrlBasePath() + constant.getUrlResourceDolarBolsa();
		
		try {
			log.info("Requesting for Dolar Bolsa");			
			
			log.info("Request to Endpoint: " + url);
			
			ResponseEntity<DolarResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, DolarResponse.class);		
			
			log.info("Status Code: " + response.getStatusCode() + " | Response: " + response.getBody());
					
			return response;			
		}catch(HttpServerErrorException ex) {
			log.error("Error calling to REST API: ".concat(url).concat(" | StatusCode: " + ex.getStatusText()));
			throw new HttpServerErrorException(ex.getStatusCode(), "Error calling to REST API: ".concat(constant.getUrlDomain() + constant.getUrlVersion()));
		}catch(HttpClientErrorException ex) {
			log.error("Error calling to REST API: ".concat(url).concat(" | StatusCode: " + ex.getStatusText()));
			throw new HttpClientErrorException(ex.getStatusCode(), "Error calling to REST API: ".concat(constant.getUrlDomain() + constant.getUrlVersion()));
		}
		
	}
	
	// APIREST - Request to get Dolar ContadoConLiqui
	public ResponseEntity<DolarResponse> dolarContadoConLiqui() {
		
		final String url = constant.getUrlDomain() + constant.getUrlVersion() + constant.getUrlBasePath() + constant.getUrlResourceDolarContadoConLiqui();
		
		try {
			log.info("Requesting for Dolar ContadoConLiqui");
						
			log.info("Request to Endpoint: " + url);
			
			ResponseEntity<DolarResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, DolarResponse.class);		
			
			log.info("Status Code: " + response.getStatusCode() + " | Response: " + response.getBody());
					
			return response;			
		}catch(HttpServerErrorException ex) {
			log.error("Error calling to REST API: ".concat(url).concat(" | StatusCode: " + ex.getStatusText()));
			throw new HttpServerErrorException(ex.getStatusCode(), "Error calling to REST API: ".concat(constant.getUrlDomain() + constant.getUrlVersion()));
		}catch(HttpClientErrorException ex) {
			log.error("Error calling to REST API: ".concat(url).concat(" | StatusCode: " + ex.getStatusText()));
			throw new HttpClientErrorException(ex.getStatusCode(), "Error calling to REST API: ".concat(constant.getUrlDomain() + constant.getUrlVersion()));
		}
		
	}
	
	// APIREST - Request to get Dolar Tarjeta
	public ResponseEntity<DolarResponse> dolarTarjeta() {
		
		final String url = constant.getUrlDomain() + constant.getUrlVersion() + constant.getUrlBasePath() + constant.getUrlResourceDolarTarjeta();
		
		try {
			log.info("Requesting for Dolar Tarjeta");			
			
			log.info("Request to Endpoint: " + url);
			
			ResponseEntity<DolarResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, DolarResponse.class);			
			
			log.info("Status Code: " + response.getStatusCode() + " | Response: " + response.getBody());
					
			return response;
		}catch(HttpServerErrorException ex) {
			log.error("Error calling to REST API: ".concat(url).concat(" | StatusCode: " + ex.getStatusText()));
			throw new HttpServerErrorException(ex.getStatusCode(), "Error calling to REST API: ".concat(constant.getUrlDomain() + constant.getUrlVersion()));
		}catch(HttpClientErrorException ex) {
			log.error("Error calling to REST API: ".concat(url).concat(" | StatusCode: " + ex.getStatusText()));
			throw new HttpClientErrorException(ex.getStatusCode(), "Error calling to REST API: ".concat(constant.getUrlDomain() + constant.getUrlVersion()));
		}
		
	}
	
	// APIREST - Request to get Dolar Mayorista
	public ResponseEntity<DolarResponse> dolarMayorista() {		
		
		final String url = constant.getUrlDomain() + constant.getUrlVersion() + constant.getUrlBasePath() + constant.getUrlResourceDolarMayorista();
		
		try {
			log.info("Requesting for Dolar Mayorista");
			
			log.info("Request to Endpoint: " + url);
			
			ResponseEntity<DolarResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, DolarResponse.class);			
			
			log.info("Status Code: " + response.getStatusCode() + " | Response: " + response.getBody());
					
			return response;
		}catch(HttpServerErrorException ex) {
			log.error("Error calling to REST API: ".concat(url).concat(" | StatusCode: " + ex.getStatusText()));
			throw new HttpServerErrorException(ex.getStatusCode(), "Error calling to REST API: ".concat(constant.getUrlDomain() + constant.getUrlVersion()));
		}catch(HttpClientErrorException ex) {
			log.error("Error calling to REST API: ".concat(url).concat(" | StatusCode: " + ex.getStatusText()));
			throw new HttpClientErrorException(ex.getStatusCode(), "Error calling to REST API: ".concat(constant.getUrlDomain() + constant.getUrlVersion()));
		}
			
	}
	
	// APIREST - Request to get Dolar Cripto
	public ResponseEntity<DolarResponse> dolarCripto() {
		
		final String url = constant.getUrlDomain() + constant.getUrlVersion() + constant.getUrlBasePath() + constant.getUrlResourceDolarCripto();
		
		try {
			log.info("Requesting for Dolar Cripto");
			
			log.info("Request to Endpoint: " + url);
			
			ResponseEntity<DolarResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, DolarResponse.class);			
			
			log.info("Status Code: " + response.getStatusCode() + " | Response: " + response.getBody());
					
			return response;
		}catch(HttpServerErrorException ex) {
			log.error("Error calling to REST API: ".concat(url).concat(" | StatusCode: " + ex.getStatusText()));
			throw new HttpServerErrorException(ex.getStatusCode(), "Error calling to REST API: ".concat(constant.getUrlDomain() + constant.getUrlVersion()));
		}catch(HttpClientErrorException ex) {
			log.error("Error calling to REST API: ".concat(url).concat(" | StatusCode: " + ex.getStatusText()));
			throw new HttpClientErrorException(ex.getStatusCode(), "Error calling to REST API: ".concat(constant.getUrlDomain() + constant.getUrlVersion()));
		}
		
	}
	
	
	
}
