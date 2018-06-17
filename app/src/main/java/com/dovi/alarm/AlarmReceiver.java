package com.dovi.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.lang.reflect.Array;

/**
 * Created by dsm2016 on 2018-06-08.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final int number = intent.getIntExtra("number", -1);

        if(number == -1) {
            Log.i("Error", "AlarmReceiver Intent error");
            return;
        }

        final String[] week = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

    }
}
