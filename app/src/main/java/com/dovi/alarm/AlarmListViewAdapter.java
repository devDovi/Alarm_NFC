package com.dovi.alarm;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dsm2016 on 2018-05-15.
 */

public class AlarmListViewAdapter extends BaseAdapter {
    private ArrayList<AlarmListViewItem> alarmListViewItemList = new ArrayList<AlarmListViewItem>();

    public AlarmListViewAdapter() {

    }

    @Override
    public int getCount() {
        return alarmListViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return alarmListViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_alarmitem, parent, false);
        }

        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkbox1);

        TextView textView1 = (TextView) convertView.findViewById(R.id.textView1);
        TextView textView2 = (TextView) convertView.findViewById(R.id.textView2);
        TextView textView3 = (TextView) convertView.findViewById(R.id.textView3);

        AlarmListViewItem alarmListViewItem = alarmListViewItemList.get(position);

        checkBox.setChecked(alarmListViewItem.getIsChecked());
        textView1.setText(alarmListViewItem.getTextStr1());
        textView2.setText(alarmListViewItem.getTextStr2());
        textView3.setText(alarmListViewItem.getTextStr3());

        return convertView;
    }

    public void addItem() {
        AlarmListViewItem item = new AlarmListViewItem();

        item.setIsChecked(true);
        item.setTextStr1("00:00");
        item.setTextStr2("AMPM");
        item.setTextStr3("DAYDAY");

        alarmListViewItemList.add(item);
    }

    // -> Move to Date / Time
    public void addItem(boolean isChecked, String text1, String text2, String text3) {
        AlarmListViewItem item = new AlarmListViewItem();

        item.setIsChecked(isChecked);
        item.setTextStr1(text1);
        item.setTextStr2(text2);
        item.setTextStr3(text3);

        alarmListViewItemList.add(item);
    }
}
