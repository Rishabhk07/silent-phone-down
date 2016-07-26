package com.example.hptouchsmart.phonedown;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.IBinder;
import android.util.Log;

public class screenOff extends Service implements SensorEventListener {

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
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this , sensor, SensorManager.SENSOR_DELAY_NORMAL);


        return START_STICKY;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        if(event.values[2] < -8){
            Log.d(TAG," on sensor changed z value changed ");
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC , 0 , AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
        }
        if(event.values[2] > 8){
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC ,audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC) , AudioManager.FLAG_PLAY_SOUND);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
