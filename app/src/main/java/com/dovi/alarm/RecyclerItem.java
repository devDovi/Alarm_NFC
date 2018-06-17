package com.dovi.alarm;

/**
 * Created by dsm2016 on 2018-05-31.
 */

public class RecyclerItem {
    private int itemNum;

    private boolean isChecked;

    private String textTime;
    private String textDate;

    public RecyclerItem(boolean isChecked, String textTime, String textDate) {
        setIsChecked(isChecked);
        setTextTime(textTime);
        setTextDate(textDate);
    }

    public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean checked) {
        isChecked = checked;
    }

    public String getTextTime() {
        return textTime;
    }

    public void setTextTime(String textTime) {
        this.textTime = textTime;
    }

    public String getTextDate() {
        return textDate;
    }

    public void setTextDate(String textDate) {
        this.textDate = textDate;
    }

}
