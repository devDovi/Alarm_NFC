package com.dovi.alarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends Activity {

    private AlarmManager mManager;
    private Calendar mCalendar;

    private NotificationManager mNotification;


    private Button buttonAlarm;
    private Button buttonNFC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNotification = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        mManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        ListView listViewAlarm;
        AlarmListViewAdapter adapter;

        adapter = new AlarmListViewAdapter();
        listViewAlarm = (ListView) findViewById(R.id.listViewAlarm);
        listViewAlarm.setAdapter(adapter);

        adapter.addItem(true, "text1", "text2", "text3");
        adapter.addItem(true, "text1", "text2", "text3");
        adapter.addItem(true, "text1", "text2", "text3");
        adapter.addItem(false, "text1", "text2", "text3");

        buttonAlarm = (Button)findViewById(R.id.buttonAlarm);
        buttonAlarm.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlarm();
            }
        });

        buttonNFC = (Button)findViewById(R.id.buttonNFC);
        buttonNFC.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, SetNFCActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }


    private void setAlarm() {
        mCalendar = Calendar.getInstance();
        mCalendar.add(Calendar.SECOND, 5);
        mManager.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pendingIntent());
        Log.i("HelloAlarmActivity", mCalendar.getTime().toString());
    }

    private PendingIntent pendingIntent() {
        Intent i = new Intent(getApplicationContext(), AlarmLockActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
        return pi;
    }
}
