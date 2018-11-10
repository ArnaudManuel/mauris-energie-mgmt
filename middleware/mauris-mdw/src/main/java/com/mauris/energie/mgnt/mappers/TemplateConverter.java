package com.mauris.energie.mgnt.mappers;

import com.mauris.energie.mgnt.ambrosusTemplate.DataTemplate;
import com.mauris.energie.mgnt.ambrosusTemplate.EventTemplate;
import com.mauris.energie.mgnt.ambrosusTemplate.ResultsTemplate;
import com.mauris.energie.mgnt.model.History;
import com.mauris.energie.mgnt.model.Measure;

import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Arnaud on 10.11.2018.
 */
public class TemplateConverter {
	private static ArrayList<String> kwh = new ArrayList<>();
	
	static {
		kwh.add("1-1:1.29.0");
		kwh.add("1-1:2.29.0");
	}

    public static History toHistory(EventTemplate template){
      History history = new History();
        for (ResultsTemplate value : template.getResults())
            convert(value, history);
        return  history;
    }

    private static void convert(ResultsTemplate resultsTemplate, History container){
        Date timeStamp = new Date(resultsTemplate.getContent().getIdData().getTimestamp()*1000l);
        for (DataTemplate data : resultsTemplate.getContent().getData()){
        	
        	if(kwh.contains(data.getObis())) {
        		data.setMeasure(""+(data.getMeasure()/4));
        		data.setUnit("KW");
        	}
        	
            container.addMeasure( data.getObis().toString(),new Measure(timeStamp, data.getMeasure(), data.getUnit()));
        }
    }
    

}
