package com.ar.alegla.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ar.alegla.models.DolarResponse;
import com.ar.alegla.models.DolarStatusResponse;
import com.ar.alegla.models.Response;
import com.ar.alegla.service.RestApiService;

import sun.security.provider.certpath.SunCertPathBuilderException;

@RestController
@RequestMapping("/backend/services/dolar_service")
public class Controller {
	
	private static final Logger log = LoggerFactory.getLogger(Controller.class);
	
	
	@Autowired
	private RestApiService service;

	@GetMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Response> getDollarApiStatus() throws SunCertPathBuilderException{		
		log.info("The resource /status, received the request... Starting");
		ResponseEntity<DolarStatusResponse> response = service.statusService();
		log.info("The request to the resource /api/status Ended");
		return new ResponseEntity<Response>(new Response(String.valueOf(response.getStatusCode().value()), response.getBody()), response.getStatusCode());
	}
	
	@GetMapping(value = "/allTypes", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Response> getAllTypeDolar() throws SunCertPathBuilderException{
		log.info("The resource /allTypes, received the request... Starting");
		ResponseEntity<DolarResponse[]> response = service.allTypesOfDolar();
		log.info("The request to the resource /allTypes ended");
		return new ResponseEntity<Response>(new Response(String.valueOf(response.getStatusCode().value()), response.getBody()), response.getStatusCode());
	}
	
	@GetMapping(value = "/dolarOficial", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Response> getDolarOficial() throws SunCertPathBuilderException{
		log.info("The resource /dolarOficial, received the request... Starting");
		ResponseEntity<DolarResponse> response = service.dolarOficial();
		log.info("The request to the resource /dolarOficial ended");
		return new ResponseEntity<Response>(new Response(String.valueOf(response.getStatusCode().value()), response.getBody()), response.getStatusCode());
	}
	
	@GetMapping(value = "/dolarBlue", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Response> getDolarBlue() throws SunCertPathBuilderException{
		log.info("The resource /dolarBlue, received the request... Starting");
		ResponseEntity<DolarResponse> response = service.dolarBlue();
		log.info("The request to the resource /dolarBlue ended");
		return new ResponseEntity<Response>(new Response(String.valueOf(response.getStatusCode().value()), response.getBody()), response.getStatusCode());
	}
	
	@GetMapping(value = "/dolarBolsa", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Response> getDolarBolsa() throws SunCertPathBuilderException{
		log.info("The resource /dolarBolsa, received the request... Starting");
		ResponseEntity<DolarResponse> response = service.dolarBolsa();
		log.info("The request to the resource /dolarBolsa ended");
		return new ResponseEntity<Response>(new Response(String.valueOf(response.getStatusCode().value()), response.getBody()), response.getStatusCode());
	}
	
	@GetMapping(value = "/dolarContadoConLiqui", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Response> getDolardolarContadoConLiqui() throws SunCertPathBuilderException{
		log.info("The resource /dolardolarContadoConLiqui, received the request... Starting");
		ResponseEntity<DolarResponse> response = service.dolarContadoConLiqui();
		log.info("The request to the resource /dolardolarContadoConLiqui ended");
		return new ResponseEntity<Response>(new Response(String.valueOf(response.getStatusCode().value()), response.getBody()), response.getStatusCode());
	}
		
	@GetMapping(value = "/dolarTarjeta", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Response> getDolarTarjeta() throws SunCertPathBuilderException{
		log.info("The resource /dolarTarjeta, received the request... Starting");
		ResponseEntity<DolarResponse> response = service.dolarTarjeta();
		log.info("The request to the resource /dolarTarjeta ended");
		return new ResponseEntity<Response>(new Response(String.valueOf(response.getStatusCode().value()), response.getBody()), response.getStatusCode());
	}
	
	
	@GetMapping(value = "/dolarMayorista", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Response> getDolarMayorista() throws SunCertPathBuilderException{
		log.info("The resource /dolarMayorista, received the request... Starting");
		ResponseEntity<DolarResponse> response = service.dolarMayorista();
		log.info("The request to the resource /dolarMayorista ended");
		return new ResponseEntity<Response>(new Response(String.valueOf(response.getStatusCode().value()), response.getBody()), response.getStatusCode());
	}
	
	
	@GetMapping(value = "/dolarCripto", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Response> getDolarCripto() throws SunCertPathBuilderException{
		log.info("The resource /dolarCripto, received the request... Starting");
		ResponseEntity<DolarResponse> response = service.dolarCripto();
		log.info("The request to the resource /dolarCripto ended");
		return new ResponseEntity<Response>(new Response(String.valueOf(response.getStatusCode().value()), response.getBody()), response.getStatusCode());
	}
	

}
