package com.mauris.energie.mgnt.mappers;

import com.mauris.energie.mgnt.ambrosusTemplate.DataTemplate;
import com.mauris.energie.mgnt.ambrosusTemplate.EventTemplate;
import com.mauris.energie.mgnt.ambrosusTemplate.ResultsTemplate;
import com.mauris.energie.mgnt.model.History;
import com.mauris.energie.mgnt.model.Measure;

import java.util.Date;


/**
 * Created by Arnaud on 10.11.2018.
 */
public class TemplateConverter {

    public static History toHistory(EventTemplate template){
      History history = new History();
        for (ResultsTemplate value : template.getResults())
            convert(value, history);
        return  history;
    }

    private static void convert(ResultsTemplate resultsTemplate, History container){
        Date timeStamp = new Date(resultsTemplate.getContent().getIdData().getTimestamp()*1000);
        for (DataTemplate data : resultsTemplate.getContent().getData()){
            container.addMeasure( data.getObis().toString(),new Measure(timeStamp, data.getMeasure(), data.getUnit()));
        }
    }
}
