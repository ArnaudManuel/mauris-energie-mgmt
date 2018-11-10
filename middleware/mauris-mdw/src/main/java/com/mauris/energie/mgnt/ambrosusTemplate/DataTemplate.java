package com.mauris.energie.mgnt.ambrosusTemplate;

import lombok.Getter;
import lombok.Setter;

public class DataTemplate {

	String measure;
	String unit;
	String obis;

	public DataTemplate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public double getMeasure() {
		measure = measure.replaceFirst(",", ".");
		measure = measure.indexOf(".") < 0 ? measure : measure.replaceAll("0*$", "").replaceAll("\\.$", "");
		return Double.parseDouble(this.measure);
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getObis() {
		return obis;
	}

	public void setObis(String obis) {
		this.obis = obis;
	}

	@Override
	public String toString() {
		return "DataTemplate [measure=" + measure + ", unit=" + unit + ", obis=" + obis + "]";
	}
}
