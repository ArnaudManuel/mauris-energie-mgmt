package com.mauris.energie.mgnt.model;

import java.util.function.Function;

public enum Obis {

	ACTIVE,REACTIVE,PRODUCTION,OTHER;
	

	
	public static Obis fromString(String arg) {
		switch (arg) {
		case "1-1:1.29.0":
			return ACTIVE;
		case "1-1:1.5.0":
			return ACTIVE;
		case "1-1:5.5.0":
			return REACTIVE;
		case "1-1:2.5.0":
			return PRODUCTION;
		case "1-1:2.29.0":
			return PRODUCTION;
		default:
			return OTHER;
			
		}
	}
}
