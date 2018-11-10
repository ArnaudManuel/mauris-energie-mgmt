package com.mauris.energie.mgnt.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by Arnaud on 10.11.2018.
 */
@Data
public class Measure {
    private Date timeStamp;
    private double measure;
    private String unit;

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getMeasure() {
        return measure;
    }

    public void setMeasure(double measure) {
        this.measure = measure;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Measure(Date timeStamp, double measure, String unit) {
        this.timeStamp = timeStamp;
        this.measure = measure;
        this.unit = unit;
    }
}
