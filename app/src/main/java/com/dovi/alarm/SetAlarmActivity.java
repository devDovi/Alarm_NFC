package com.dovi.alarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by dsm2016 on 2018-05-15.
 */

public class SetAlarmActivity extends Activity {
    private TimePicker timePicker;

    private int hour = -1;
    private int min = -1;
    private int index = -1;
    private boolean isChecked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setalarm);

        timePicker = (TimePicker) findViewById(R.id.timePicker);

        Calendar calendar = Calendar.getInstance();

        Intent intent = getIntent();
        Log.d("requestCode", Integer.toString(intent.getIntExtra("requestCode", -1)));

        if(intent.getIntExtra("requestCode", -1) == MainActivity.ALARM_CREATE) {
            Log.d("requestCode", "ALARM_CREATE");
            timePicker.setHour(calendar.get(Calendar.HOUR_OF_DAY));
            timePicker.setMinute(calendar.get(Calendar.MINUTE) + 1);
            isChecked = true;
        } else if(intent.getIntExtra("requestCode", -1) == MainActivity.ALARM_EDIT) {
            Log.d("requestCode", "ALARM_EDIT");
            Log.d("INTENT_HOUR", Integer.toString(intent.getIntExtra(MainActivity.INTENT_HOUR, -1)));
            Log.d("INTENT_MIN", Integer.toString(intent.getIntExtra(MainActivity.INTENT_MIN, -1)));
            Log.d("INTENT_INDEX", Integer.toString(intent.getIntExtra(MainActivity.INTENT_INDEX, -1)));

            timePicker.setHour(intent.getIntExtra(MainActivity.INTENT_HOUR, -1));
            timePicker.setMinute(intent.getIntExtra(MainActivity.INTENT_MIN, -1));
            index = intent.getIntExtra(MainActivity.INTENT_INDEX, -1);
            isChecked = intent.getBooleanExtra(MainActivity.INTENT_ISCHECKED, false);
        }

        Log.d("Time Picker time", Integer.toString(timePicker.getHour()) + ":" + Integer.toString(timePicker.getMinute()));

        setButton();
    }

    protected void setButton() {
        findViewById(R.id.buttonVerify).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SetAlarmActivity", "ButtonVerify Clicked");

                Intent intent = getIntent();
                Intent data = new Intent();

                if(intent.getIntExtra("requestCode", -1) == MainActivity.ALARM_CREATE) {
                    data.putExtra(MainActivity.INTENT_ISCHECKED, true);
                    data.putExtra(MainActivity.INTENT_HOUR, timePicker.getHour());
                    data.putExtra(MainActivity.INTENT_MIN, timePicker.getMinute());
                } else if(intent.getIntExtra("requestCode", -1) == MainActivity.ALARM_EDIT) {
                    data.putExtra(MainActivity.INTENT_ISCHECKED, intent.getBooleanExtra(MainActivity.INTENT_ISCHECKED, false));
                    data.putExtra(MainActivity.INTENT_HOUR, timePicker.getHour());
                    data.putExtra(MainActivity.INTENT_MIN, timePicker.getMinute());
                    data.putExtra(MainActivity.INTENT_INDEX, intent.getIntExtra(MainActivity.INTENT_INDEX, -1));
                }

                setResult(RESULT_OK, data);
                finish();
            }
        });

        findViewById(R.id.buttonCancel).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                setResult(RESULT_CANCELED, data);
                finish();
            }
        });

    }
}
