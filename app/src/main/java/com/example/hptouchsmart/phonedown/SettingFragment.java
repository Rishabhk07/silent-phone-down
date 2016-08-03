package com.example.hptouchsmart.phonedown;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by hp TouchSmart on 8/3/2016.
 */
public class SettingFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefrences);
    }

}
