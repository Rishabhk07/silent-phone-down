package com.silentflip.hptouchsmart.phonedown;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by hp TouchSmart on 8/3/2016.
 */
public class SettingFragment extends PreferenceFragment {

    boolean DEBUG = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefrences);
        if(DEBUG)Log.d("TAG" , "setting fragment called ");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("CHECK"  , true);
        editor.commit();

    }



}
