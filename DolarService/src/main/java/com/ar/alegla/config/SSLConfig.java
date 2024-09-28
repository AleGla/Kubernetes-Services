package com.ar.alegla.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;


@Configuration
public class SSLConfig {

	private static final Logger log = LoggerFactory.getLogger(SSLConfig.class);
	
	@Value("${ssl.insecure.enable}")
	private boolean isInsecure;
	
	@Value("${ssl.cacerts.file}")
	private String file;
	
	@Value("${ssl.cacerts.password}")
	private String pass;
	
	@Bean
	RestTemplate getRestTemaplate() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException,
			CertificateException, FileNotFoundException, IOException {
		return controlSSL(isInsecure, file, pass);
	}

	private static RestTemplate controlSSL(boolean isInsecure, String fileLocation, String pass) {
		SSLContext sslContext = null;
		if (isInsecure) {
			try {
				log.warn("---->>>> The call to the api is INSECURE - (UPDATE THE CERTIFICATE AS YOU CAN SOON!!!) <<<<----");
				TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
				sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
						.build();
			} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			log.info("---->>>> SSL for RestTemplate is configured <<<<----");
			try {
				sslContext = SSLContextBuilder.create()
						.loadTrustMaterial(ResourceUtils.getFile(fileLocation), pass.toCharArray()).build();
			} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException | CertificateException
					| IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

		return new RestTemplate(requestFactory);

	}
}
