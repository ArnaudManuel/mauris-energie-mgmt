package com.mauris.energie.mgnt.ambrosusTemplate;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


public class ContentTemplate {

	
	IdDataTemplate idData;
	List<DataTemplate> data;
	
	public ContentTemplate() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public IdDataTemplate getIdData() {
		return idData;
	}
	public void setIdData(IdDataTemplate idData) {
		this.idData = idData;
	}
	public List<DataTemplate> getData() {
		return data;
	}
	public void setData(List<DataTemplate> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ContentTemplate [idData=" + idData.toString() + ", data=" + data.toString() + "]";
	}
}
