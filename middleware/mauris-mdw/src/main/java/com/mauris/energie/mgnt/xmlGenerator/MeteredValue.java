package com.mauris.energie.mgnt.xmlGenerator;

import java.util.List;
import java.util.Map.Entry;

import com.mauris.energie.mgnt.model.Measure;

public class MeteredValue {
	
	private String obis;
	private String readingDate;
	private String time;
	private String value;
	
	
	public String getObis() {
		return obis;
	}
	public void setObis(String obis) {
		this.obis = obis;
	}
	public String getReadingDate() {
		return readingDate;
	}
	public void setReadingDate(String readingDate) {
		this.readingDate = readingDate;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
