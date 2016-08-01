package com.example.hptouchsmart.phonedown;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class screenOff extends Service implements SensorEventListener {

    boolean music;
    boolean ringtone;
    boolean alarm;
    boolean flag = true;

    public static final String TAG = "TAG";
    BroadcastReceiver broadcastReceiver;
    SensorManager sensorManager;
    public screenOff() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");

    }

    @Override
    public void onCreate() {
        super.onCreate();



        Log.d(TAG , "Service oncreate called !! ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG ,"Service fired");

        SharedPreferences sharedPreferences = getSharedPreferences("PhoneDown", MODE_PRIVATE);
        music = sharedPreferences.getBoolean("MUSIC" , false);
        ringtone = sharedPreferences.getBoolean("RINGTONE" , false);
        alarm = sharedPreferences.getBoolean("ALARM" , false);

        Log.d("MUSIC" , "value : " + music );
        Log.d("RINGTONE" , "value : " + ringtone );
        Log.d("ALARM" , "value : " + alarm );

        flag = true;


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this , sensor, SensorManager.SENSOR_DELAY_NORMAL);


        return START_STICKY;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        Log.d("MUSIC" , "value : " + music );
        Log.d("RINGTONE" , "value : " + ringtone );
        Log.d("ALARM" , "value : " + alarm );

        if(event.values[2] < -8 && flag){
            Log.d(TAG," on sensor changed z value changed ");

            if(music == true) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
            }
            if(ringtone == true){
                audioManager.setStreamVolume(AudioManager.STREAM_RING, 0, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
            }
            if(alarm == true){
                audioManager.setStreamVolume(AudioManager.STREAM_ALARM , 0, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
            }
        }
        if(event.values[2] > 8 && flag){
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC ,audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC) , AudioManager.FLAG_PLAY_SOUND);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onDestroy() {
        Log.d(TAG , "Service Destroyed");
        Toast.makeText(screenOff.this, "service Destroyed !! ", Toast.LENGTH_SHORT).show();
        flag = false;
        super.onDestroy();

    }
}
