package com.example.hptouchsmart.phonedown;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class startListening extends BroadcastReceiver {

    public static final String TAG = "TAG";
    public startListening() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        Log.d(TAG , "On Screen off");

    }
}
