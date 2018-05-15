package com.dovi.alarm;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by dsm2016 on 2018-05-09.
 */

public class AlarmListViewItem {
    private boolean isChecked;

    private String textStr1;
    private String textStr2;
    private String textStr3;

    private Drawable alarmIcon;

    public void setIsChecked(boolean checked) {
        isChecked = checked;
    }

    public void setTextStr1(String text) {
        textStr1 = text;
    }

    public void setTextStr2(String text) {
        textStr2 = text;
    }

    public void setTextStr3(String text) {
        textStr3 = text;
    }

    public void setAlarmIcon(Drawable img) {
        alarmIcon = img;
    }

    public boolean getIsChecked() {
        return this.isChecked;
    }

    public String getTextStr1() {
        return this.textStr1;
    }

    public String getTextStr2() {
        return this.textStr2;
    }

    public String getTextStr3() {
        return this.textStr3;
    }
}
