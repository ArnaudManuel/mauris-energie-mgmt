package com.mauris.energie.mgnt.ambrosusTemplate;

import lombok.Data;
import static java.util.Arrays.asList;

import java.util.Date;

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

	public EventTemplate getAmbrosus(String pod, Date from, Date to) {
		// "CH1014001234500000000000000006860_test";

		String url = String.format(
				"https://gateway-test.ambrosus.com/events?data[pod]=%s&fromTimestamp=%d&toTimestamp=%d", pod,
				(from.getTime() / 1000), (to.getTime() / 1000));
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.setMessageConverters(asList(new MappingJackson2HttpMessageConverter()));
		HttpHeaders headers = new HttpHeaders();
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<EventTemplate> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity,
				EventTemplate.class);
		EventTemplate events = responseEntity.getBody();

		return events;
	}
}
