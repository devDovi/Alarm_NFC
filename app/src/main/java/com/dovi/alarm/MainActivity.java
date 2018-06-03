package com.dovi.alarm;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends Activity {

    public static final int ALARM_CREATE = 1;
    public static final int ALARM_EDIT = 2;

    private AlarmManager mManager;
    private Calendar mCalendar;

    private NotificationManager mNotification;

    private ArrayList<RecyclerItem> recyclerItemArrayList = new ArrayList<RecyclerItem>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private LinearLayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNotification = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        mManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        setRecyclerView();
        setButton();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            if(requestCode == ALARM_CREATE) {
                Log.d("onActivityResult", "ALARM_CREATE");

                int hour = data.getIntExtra("Hour", -1);
                int min = data.getIntExtra("Min", -1);

                if(hour == -1 && min == -1) {
                    return;
                }

                String strTime = Integer.toString(hour) + ":" + Integer.toString(min);
                String strAMPM;
                if(hour > 12) {
                    strAMPM = "PM";
                } else {
                    strAMPM = "AM";
                }

                RecyclerItem recyclerItem = new RecyclerItem(true, strTime, strAMPM, "None");
                recyclerItemArrayList.add(recyclerItem);
                recyclerViewAdapter.notifyDataSetChanged();

            } else if(requestCode == ALARM_EDIT) {

            }
        }
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
                startActivityForResult(intent, ALARM_CREATE);
            }
        });

        findViewById(R.id.buttonNFC).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SetNFCActivity.class);
                startActivityForResult(intent, 77);
            }
        });
    }
}
