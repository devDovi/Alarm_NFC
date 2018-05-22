package com.dovi.alarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by dsm2016 on 2018-05-15.
 */

public class SetAlarmActivity extends Activity {
    private Button buttonVerify;
    private Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setalarm);


        findViewById(R.id.buttonVerify).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("result", "Verify");
                intent.putExtra("Alarm", "");
                finish();
            }
        });

        findViewById(R.id.buttonCancel).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


}
