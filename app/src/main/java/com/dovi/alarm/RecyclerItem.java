package com.dovi.alarm;

import java.util.Calendar;

/**
 * Created by dsm2016 on 2018-05-31.
 */

public class RecyclerItem {
    private boolean isChecked;

    private String textTime;
    private String textAMPM;
    private String textDate;

    public RecyclerItem(boolean isChecked, String textTime, String textAMPM, String textDate) {
        this.isChecked = isChecked;
        this.textTime = textTime;
        this.textAMPM = textAMPM;
        this.textDate = textDate;
    }

    public boolean getIsChecked() {
        return this.isChecked;
    }

    public String getTextTime() {
        return this.textTime;
    }

    public String getTextAMPM() {
        return this.textAMPM;
    }

    public String getTextDate() {
        return this.textDate;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public void setTextTime(String textTime){
        this.textTime = textTime;
    }

    public void setTextAMPM(String textAMPM){
        this.textAMPM = textAMPM;
    }

    public void setTextDate(String textDate){
        this.textDate = textDate;
    }
}
