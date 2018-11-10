package com.mauris.energie.mgnt.ambrosusTemplate;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class EventTemplate {
	int resultCount;
	
	List<ResultsTemplate> results;

	public EventTemplate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public List<ResultsTemplate> getResults() {
		return results;
	}

	public void setResults(List<ResultsTemplate> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "EventTemplate{" +
				"resultCount=" + resultCount +
				", results=" + results.toString() +
				'}';
	}


}
