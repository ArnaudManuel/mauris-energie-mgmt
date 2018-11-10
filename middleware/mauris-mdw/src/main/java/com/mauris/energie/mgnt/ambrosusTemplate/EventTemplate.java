package com.mauris.energie.mgnt.ambrosusTemplate;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class EventTemplate {
	int resultCount;
	
	List<ResultsTemplate> results;

}
