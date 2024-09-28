package com.ar.alegla.utils;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class EnvConfig {
	
	@Autowired
	private Environment env;

	
	public String getUrlDomain() {
		return env.getProperty("rest.service.url.domain");
	}
	
	public String getUrlVersion() {
		return env.getProperty("rest.service.url.version");
	}

	public String getUrlResourceStatus() {
		return env.getProperty("rest.service.url.resource.status");
	}

	public String getUrlBasePath() {
		return env.getProperty("rest.service.url.resource.basePath");
	}
	
	public String getUrlResourceDolarOficial() {
		return env.getProperty("rest.service.url.resource.dolarOficial");
	}
	
	public String getUrlResourceDolarBlue() {
		return env.getProperty("rest.service.url.resource.dolarBlue");
	}
	
	public String getUrlResourceDolarBolsa() {
		return env.getProperty("rest.service.url.resource.dolarBolsa");
	}
	
	public String getUrlResourceDolarContadoConLiqui() {
		return env.getProperty("rest.service.url.resource.dolarContadoConLiqui");
	}
	
	public String getUrlResourceDolarTarjeta() {
		return env.getProperty("rest.service.url.resource.dolarTarjeta");
	}
	
	public String getUrlResourceDolarMayorista() {
		return env.getProperty("rest.service.url.resource.dolarMayorista");
	}
	
	public String getUrlResourceDolarCripto() {
		return env.getProperty("rest.service.url.resource.dolarCripto");
	}
	
	
	

	
}
