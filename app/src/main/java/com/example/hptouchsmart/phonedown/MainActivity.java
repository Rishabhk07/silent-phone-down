package com.example.hptouchsmart.phonedown;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver broadcastReceiver;
    IntentFilter intentFilter;
    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                Intent i = new Intent(MainActivity.this , screenOff.class);
                startService(i);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG , "onResume Called");
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
