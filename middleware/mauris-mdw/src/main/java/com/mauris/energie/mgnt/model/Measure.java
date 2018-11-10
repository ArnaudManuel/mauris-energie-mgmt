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
}
