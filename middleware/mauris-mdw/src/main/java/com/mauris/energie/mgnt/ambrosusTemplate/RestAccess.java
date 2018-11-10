package com.mauris.energie.mgnt.ambrosusTemplate;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Data
public class RestAccess {

	public EventTemplate getSample() {
	RestTemplate restTemplate = new RestTemplate();
	
	String fooResourceUrl = "https://gateway-test.ambrosus.com/events?perPage=1000&data[pod]=CH1014001234500000000000000006860_test";
	
	EventTemplate events = restTemplate.getForObject(fooResourceUrl, EventTemplate.class);
	return events;
	}
}
