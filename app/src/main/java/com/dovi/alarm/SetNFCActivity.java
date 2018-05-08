package com.dovi.alarm;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by dsm2016 on 2018-05-07.
 */

public class SetNFCActivity extends Activity {

    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private IntentFilter[] mFilters;
    private String[][] mTechLists;
    private String strReadResult;
    private Tag tag;
    private IsoDep tagcomm;

    private Button buttonStartNFC;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setnfc);

        /* Setting View */

        buttonStartNFC = (Button) findViewById(R.id.buttonStartNFC);
        buttonStartNFC.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setNFC();
            }
        });

        textViewResult = (TextView) findViewById(R.id.textViewResult);

        /* Setting NFC */
        mAdapter = NfcAdapter.getDefaultAdapter(this);

        Intent targetIntent = new Intent(this, SetNFCActivity.class);
        targetIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        mPendingIntent = PendingIntent.getActivity(this, 0, targetIntent, 0);

        try {
            ndef.addDataType("*/*");
        } catch (Exception e) {
            throw new RuntimeException("fail", e);
        }

        mFilters = new IntentFilter[] {ndef, };
        mTechLists = new String[][] {new String[]{NfcA.class.getName()}};


    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mAdapter != null) {
            mAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters, mTechLists);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mAdapter != null) {
            mAdapter.disableForegroundDispatch(this);
        }
    }

    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if(intent == null) {
            return;
        }

        Tag detectedTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);



        /*
        Tag tag = passedIntent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (tag != null) {
            byte[] tagId = tag.getId();
            strReadResult = Integer.toHexString(tagId);
        }

        if (passedIntent != null) {
            processTag(passedIntent);
        }
        */
    }

    public void pushNFC(int count) {
        NdefMessage mMessage;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mMessage = new NdefMessage(new NdefRecord[] { NdefRecord.createTextRecord(String.valueOf(Locale.ENGLISH), "asdf")});
        }
    }

    public int scanNFC() {
        return 0;
    }

    public static String byteArrayToHexString(byte[] b) {
        int len = b.length;
        String data = new String();
        for (int i = 0; i < len; i++){
            data += Integer.toHexString((b[i] >> 4) & 0xf);
            data += Integer.toHexString(b[i] & 0xf);
        }
        return data;
    }

    private void processTag(Intent passedIntent) {
        Parcelable[] rawMsgs = passedIntent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        
        if(rawMsgs == null) {
            return;
        }

        NdefMessage[] msgs;
        
        if(rawMsgs != null) {
            msgs = new NdefMessage[rawMsgs.length];
            
            for(int i = 0 ; i < rawMsgs.length ; i++) {
                msgs[i] = (NdefMessage) rawMsgs[i];
                showTag(msgs[i]);
            }
        }


    }

    private int showTag(NdefMessage mMessage) {
       // List<>
        return 0;
    }

}