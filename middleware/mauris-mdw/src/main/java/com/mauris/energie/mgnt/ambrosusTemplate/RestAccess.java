package com.mauris.energie.mgnt.ambrosusTemplate;

import lombok.Data;
import static java.util.Arrays.asList;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestAccess {

	public EventTemplate getSample() {
	RestTemplate restTemplate = new RestTemplate();
	String fooResourceUrl = "https://gateway-test.ambrosus.com/events?perPage=1000&data[pod]=CH1014001234500000000000000006860_test";
	restTemplate.setMessageConverters(asList(new MappingJackson2HttpMessageConverter()));
    HttpHeaders headers = new HttpHeaders();
	headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

   
     headers.setContentType(MediaType.APPLICATION_JSON);
	HttpEntity<String> entity = new HttpEntity<String>(headers);
	ResponseEntity<EventTemplate> responseEntity = restTemplate.exchange(fooResourceUrl, HttpMethod.GET, entity, EventTemplate.class);
	EventTemplate events = responseEntity.getBody();

	return events;
	}
}
