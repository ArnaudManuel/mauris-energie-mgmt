package com.mauris.energie.mgnt.ambrosusTemplate;

import lombok.Getter;
import lombok.Setter;


public class IdDataTemplate {

	int timestamp;

	public IdDataTemplate() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "IdDataTemplate [timestamp=" + timestamp + "]";
	}

}
