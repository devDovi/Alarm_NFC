package com.dovi.alarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends Activity {

    public static final int ALARM_CREATE = 1;
    public static final int ALARM_EDIT = 2;

    public static final String INTENT_HOUR = "INTENT_HOUR";
    public static final String INTENT_MIN = "INTENT_MIN";
    public static final String INTENT_INDEX = "INTENT_INDEX";
    public static final String INTENT_ISCHECKED = "INTENT_ISCHECKED";
    private static final String DB_TAG = "TEST";


    private TextView textViewAlarm;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;

    private Calendar mCalendar;
    private ArrayList<RecyclerItem> recyclerItemArrayList = new ArrayList<RecyclerItem>();
    private LinearLayoutManager mLayoutManager;
    private NotificationManager mNotification;
    private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRecyclerView();
        setButton();
        textViewAlarm = (TextView) findViewById(R.id.textViewAlarm);

        mNotification = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        setAlarm();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            int hour = data.getIntExtra(INTENT_HOUR, -1);
            int min = data.getIntExtra(INTENT_MIN, -1);
            boolean isChecked = data.getBooleanExtra(INTENT_ISCHECKED, false);

            Log.d(INTENT_HOUR, Integer.toString(hour));
            Log.d(INTENT_MIN , Integer.toString(min));
            Log.d(INTENT_ISCHECKED , Boolean.toString(isChecked));

            if(hour == -1 || min == -1) {
                Log.d("Intent Error : ", "Wrong Intent Received");
            }

            if(requestCode == ALARM_CREATE) {
                Log.d("onActivityResult", "ALARM_CREATE");

                RecyclerItem recyclerItem = setRecyclerItem(isChecked, hour, min);
                if(recyclerItem == null)
                    return;

                recyclerItemArrayList.add(recyclerItem);
                recyclerViewAdapter.notifyDataSetChanged();

            } else if(requestCode == ALARM_EDIT) {
                Log.d("onActivityResult", "ALARM_EDIT");

                int index = data.getIntExtra(INTENT_INDEX, -1);
                Log.d("Main | Index  : ", Integer.toString(index));

                if(index == -1)
                    return;

                RecyclerItem changedRecyclerItem = setRecyclerItem(isChecked, hour, min);
                if(changedRecyclerItem == null)
                    return;

                recyclerItemArrayList.set(index, changedRecyclerItem);
                recyclerViewAdapter.notifyItemChanged(index);
            }
        }

        if(recyclerItemArrayList.size() == 0) {
            textViewAlarm.setText("No Setted Alarm");
        } else if (recyclerItemArrayList.size() == 1){
            textViewAlarm.setText(recyclerItemArrayList.size() + " alarm setted");
        } else {
            textViewAlarm.setText(recyclerItemArrayList.size() + " alarms setted");
        }
    }

    private void setAlarm() {
        //Set Alarms on AlarmItemLists

        mCalendar = Calendar.getInstance();
        mCalendar.add(Calendar.SECOND, 5);
        Log.i("HelloAlarmActivity", mCalendar.getTime().toString());
        alarmManager.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pendingIntent());
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), 30 * 1000, pendingIntent());
    }

    private PendingIntent pendingIntent() {
        Intent i = new Intent(getApplicationContext(), SetAlarmActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
        return pi;
    }

    private void setRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(false);

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerViewAdapter = new RecyclerViewAdapter(recyclerItemArrayList, getApplicationContext());
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void setButton() {
        findViewById(R.id.buttonAddAlarm).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SetAlarmActivity.class);
                intent.putExtra("requestCode", ALARM_CREATE);
                startActivityForResult(intent, ALARM_CREATE);
            }
        });

        findViewById(R.id.buttonStartNFC).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SetNFCActivity.class);
                intent.putExtra("requestCode", ALARM_EDIT);
                startActivityForResult(intent, 77);
            }
        });
    }

    private RecyclerItem setRecyclerItem(boolean isChecked, int hour, int min) {
        if(hour == -1 || min == -1)
            return null;

        String strHour;
        String strMin;
        String strAMPM;

        if(hour < 10) {
            strHour = "0" + Integer.toString(hour);
        } else {
            strHour = Integer.toString(hour);
        }

        if(min < 10) {
            strMin = "0" + Integer.toString(min);
        } else {
            strMin = Integer.toString(min);
        }

        String strTime = strHour + ":" + strMin;

        RecyclerItem recyclerItem = new RecyclerItem(isChecked, strTime, "None");
        return recyclerItem;
    }
}
