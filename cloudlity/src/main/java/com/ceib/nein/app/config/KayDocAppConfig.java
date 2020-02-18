package com.ceib.nein.app.config;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class KayDocAppConfig {



	public Object callKayDocRestApi(String url ,String credintials) {
		String plainCreds = credintials; //"admin:admin";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);

		HttpEntity<String> request = new HttpEntity<String>(headers);
		//String url ="http://localhost:8090/services/rest/folder/listChildren?folderId=4";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, request, Object.class);
		Object account = response.getBody();
		try {
			System.out.println("res == "+new ObjectMapper().writeValueAsString(response));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return account;

	}

	public Object callKayDocRestApiPost(String url ,String credintials) {
		String plainCreds = credintials; //"admin:admin";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		
		
		//HttpHeaders headers= new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, Object> map= new LinkedMultiValueMap<String, Object>();
		map.add("parentId", "4");
		map.add("name", "chileDir2");
		

		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);

		
		

	//	HttpEntity<String> request = new HttpEntity<String>(headers);
		
		//String url ="http://localhost:8090/services/rest/folder/listChildren?folderId=4";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.POST, request, Object.class);
		System.out.println("response == "+response);
		Object account = response.getBody();
		try {
			System.out.println("res == "+new ObjectMapper().writeValueAsString(response));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return account;

	}

}
