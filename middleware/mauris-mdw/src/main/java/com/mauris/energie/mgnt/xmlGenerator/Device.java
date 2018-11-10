package com.mauris.energie.mgnt.xmlGenerator;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mauris.energie.mgnt.model.History;
import com.mauris.energie.mgnt.model.Measure;
import com.mauris.energie.mgnt.model.VirtualPod;

@XmlRootElement(name = "device")
public class Device {

	/**
	 * pod
	 */
	private String meteringcode;

	private List<MeteredValue> meteredValue;

	public Device (VirtualPod pod, History history) {
	//	Map<String, List<Measure>> measures = history.getMeasures();

		//List<MeteredValue> meteredValue = new LinkedList<>();
	//	for(Map.Entry<String, List<Measure>> entry : measures.entrySet()){
			
			//meteredValue.add(value);
	//	}
		
		
	}

	public Device() {
	}

	@XmlElement(name = "meteringcode")
	public String getMeteringcode() {
		return meteringcode;
	}

	public void setMeteringcode(String meteringcode) {
		this.meteringcode = meteringcode;
	}

	@XmlElement(name = "meteredValue")
	public List<MeteredValue> getMeteredValue() {
		return meteredValue;
	}

	public void setMeteredValue(List<MeteredValue> meteredValue) {
		this.meteredValue = meteredValue;
	}

}
