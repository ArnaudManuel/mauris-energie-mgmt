package com.mauris.energie.mgnt.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Arnaud on 10.11.2018.
 */
// represent all measure by oris for a virtual pod
public class History {
	// oris - liste de mesure
	private Map<Obis, List<Measure>> measures = new HashMap<>();

	public void addMeasure(String oris, Measure measure) {
		addMeasure(Obis.fromString(oris), measure);
	}

	public void addMeasure(Obis oris, Measure measure) {
		List<Measure> content = measures.get(oris);
		if (content == null) {
			content = new LinkedList<>();
			measures.put(oris, content);
		}
		content.add(measure);
	}

	public Map<Obis, List<Measure>> getMeasures() {
		return measures;
	}

	public void setMeasures(Map<Obis, List<Measure>> measures) {
		this.measures = measures;
	}

	@Override
	public String toString() {
		return "History{" + "measures=" + measures + '}';
	}
	
	public double getMax(Obis obis) {
		
		return 0;
	}
}
