package com.mauris.energie.mgnt.ambrosusTemplate;

import lombok.Getter;
import lombok.Setter;


public class ResultsTemplate {

	ContentTemplate content;


	public ResultsTemplate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ContentTemplate getContent() {
		return content;
	}

	public void setContent(ContentTemplate content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ResultsTemplate [content=" + content.toString() + "]";
	}

	
	
	
}
