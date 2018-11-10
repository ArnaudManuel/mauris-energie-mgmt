package com.mauris.energie.mgnt.helper;

import com.mauris.energie.mgnt.model.History;
import com.mauris.energie.mgnt.model.Measure;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Arnaud on 10.11.2018.
 */
public class HistoryAgregator {

    public static History aggragate(History base, History... others){
        //oris - timestamp - pair
        HashMap<String, HashMap<Date, Pair>> temp = new HashMap<>();


        for (Map.Entry<String, List<Measure>> value : base.getMeasures().entrySet()){
            //first collection, free
            HashMap<Date, Pair> obisCollection = new HashMap<>();
            for (Measure measure : value.getValue())
                obisCollection.put(measure.getTimeStamp(), new Pair(measure.getMeasure(), measure.getUnit()));

            temp.put(value.getKey(), obisCollection);
        }

        for(History other : others)
            for (Map.Entry<String, List<Measure>> value : other.getMeasures().entrySet()){
                //other, check existence
                HashMap<Date, Pair> obisCollection = temp.get(value.getKey());
                if(obisCollection == null)
                    obisCollection = new HashMap<>();

                for (Measure measure : value.getValue())
                    obisCollection.put(measure.getTimeStamp(), new Pair(measure.getMeasure(), measure.getUnit()));

                temp.put(value.getKey(), obisCollection);
            }

        History concatened = new History();
        for (Map.Entry<String, HashMap<Date, Pair>> list : temp.entrySet())
            for(Map.Entry<Date, Pair> virtualMeasure : list.getValue().entrySet())
                concatened.addMeasure(list.getKey(),new Measure(virtualMeasure.getKey(), virtualMeasure.getValue().value, virtualMeasure.getValue().unit));

        return concatened;
    }

    private static class Pair{
        double value;
        String unit;

        public Pair(double value, String unit) {
            this.value = value;
            this.unit = unit;
        }
    }
}
