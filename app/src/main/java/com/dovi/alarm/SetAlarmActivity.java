package com.dovi.alarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.ToggleButton;

/**
 * Created by dsm2016 on 2018-05-15.
 */

public class SetAlarmActivity extends Activity {
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setalarm);

        timePicker = (TimePicker) findViewById(R.id.timePicker);

        setButton();
    }

    protected void setButton() {
        findViewById(R.id.buttonVerify).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                int hour = -1;
                int min = -1;

                hour = timePicker.getHour();
                min = timePicker.getMinute();

                Log.d("Times : ", Integer.toString(hour) + ":" + Integer.toString(min));

                data.putExtra("Hour", hour);
                data.putExtra("Min", min);
                //data.putExtra("Date", );

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
