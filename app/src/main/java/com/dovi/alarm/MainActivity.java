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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends Activity {

    private AlarmManager mManager;
    private Calendar mCalendar;

    private NotificationManager mNotification;


    private Button buttonAddAlarm;
    private Button buttonNFC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNotification = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        mManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        final ListView listViewAlarm;
        AlarmListViewAdapter adapter;

        listViewAlarm = (ListView) findViewById(R.id.listViewAlarm);

        adapter = new AlarmListViewAdapter();
        listViewAlarm.setAdapter(adapter);

        //adapter.addItem();

        listViewAlarm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            }
        });

        buttonAddAlarm = (Button)findViewById(R.id.buttonAddAlarm);
        buttonAddAlarm.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAlarm();
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

    private void addAlarm() {
        Intent intent = new Intent(getApplicationContext(), SetAlarmActivity.class);
        startActivity(intent);
    }

    private void setAlarm() {
        mCalendar = Calendar.getInstance();
        mCalendar.add(Calendar.SECOND, 5);
        mManager.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pendingIntent());
        Log.i("HelloAlarmActivity", mCalendar.getTime().toString());
    }

    private PendingIntent pendingIntent() {
        Intent i = new Intent(getApplicationContext(), SetAlarmActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
        return pi;
    }
}
