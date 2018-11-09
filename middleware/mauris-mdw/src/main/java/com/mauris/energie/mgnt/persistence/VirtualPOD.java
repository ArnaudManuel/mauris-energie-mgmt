package com.mauris.energie.mgnt.persistence;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class VirtualPOD {

	@Id
	String podName;
	
	List<String> content;
}
