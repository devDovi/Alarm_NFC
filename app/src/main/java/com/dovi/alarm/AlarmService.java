package com.dovi.alarm;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by dsm2016 on 2018-06-17.
 */

public class AlarmService extends Service {

    private static final String TAG = "AlarmService";

    private IBinder iBinder = new MyBinder();

    class MyBinder extends Binder {
        AlarmService getService() {
            return AlarmService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return iBinder;
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate()");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy()");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind()");
        return super.onUnbind(intent);
    }


}
