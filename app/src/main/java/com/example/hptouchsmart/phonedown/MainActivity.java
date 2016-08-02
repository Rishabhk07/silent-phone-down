package com.example.hptouchsmart.phonedown;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver broadcastReceiver;
    IntentFilter intentFilter;
    public static final String TAG = "TAG";

    boolean music = true;
    boolean ringtone = false;
    boolean alarm = false;

    CheckBox musicCB ;
    CheckBox ringtoneCB ;
    CheckBox alarmCB ;

    Button startServiceButtn;
    Button stopServiceButton;

    public static final String MUSIC_ = "music";
    public static final String RINGTONE_ = "ringtone";
    public static final String ALARM_ = "alarm";

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




                musicCB = (CheckBox) findViewById(R.id.checkBox_music);
                ringtoneCB = (CheckBox) findViewById(R.id.checkBox_ringtoone);
                alarmCB = (CheckBox) findViewById(R.id.checkBox_alarm);
                startServiceButtn = (Button) findViewById(R.id.start_button);
                stopServiceButton = (Button) findViewById(R.id.stop_button);
                drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                actionBarDrawerToggle = new ActionBarDrawerToggle(this , drawerLayout  , toolbar , R.string.drawer_open , R.string.drawer_close);


                drawerLayout.setDrawerListener(actionBarDrawerToggle);




                startServiceButtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        music = musicCB.isChecked();
                        ringtone = ringtoneCB.isChecked();
                        alarm = alarmCB.isChecked();
                        SharedPreferences sharedPreferences = getSharedPreferences("PhoneDown" , MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putBoolean("MUSIC" , music);
                        editor.putBoolean("RINGTONE" , ringtone);
                        editor.putBoolean("ALARM" , alarm);
                        editor.apply();
                        Intent i = new Intent(getBaseContext() , screenOff.class);
                        startService(i);
                    }
                });


            stopServiceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this , screenOff.class);
                    stopService(i);
                }
            });






    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG , "onResume Called");

        SharedPreferences sharedPreferences = getSharedPreferences("PhoneDown", MODE_PRIVATE);
        music = sharedPreferences.getBoolean("MUSIC" , false);
        ringtone = sharedPreferences.getBoolean("RINGTONE" , false);
        alarm = sharedPreferences.getBoolean("ALARM" , false);

        if(music == true)
            musicCB.setChecked(true);
        if(ringtone == true)
            ringtoneCB.setChecked(true);
        if(alarm == true)
            alarmCB.setChecked(true);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState();
    }
}
