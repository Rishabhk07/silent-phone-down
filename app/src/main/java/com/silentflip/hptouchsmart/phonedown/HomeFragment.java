package com.silentflip.hptouchsmart.phonedown;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends android.app.Fragment {

    boolean DEBUG = false;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View homeView = inflater.inflate(R.layout.fragment_home, container, false);

        ToggleButton toggleButton = (ToggleButton) homeView.findViewById(R.id.toggleStartService);

        SharedPreferences checkPrefrences  = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final boolean stateCheck = checkPrefrences.getBoolean("START_CHECK" , false);

        if(stateCheck){
            startService();
            toggleButton.setChecked(true);
           if(DEBUG) Log.d("TAG" , "boolean true");
        }else{
            stopService();
            toggleButton.setChecked(false);
            if(DEBUG) Log.d("TAG" , "boolean false");

        }

        if(DEBUG) Log.d("TAG" , "Home Fragment Called !!");
        homeView.requestLayout();


        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    startService();
                    Snackbar snackbar = Snackbar.make(homeView , "Silent Flip ON" , Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }else{

                    stopService();
                    Snackbar snackbar = Snackbar.make(homeView , "Silent Flip OFF" , Snackbar.LENGTH_SHORT);
                    if(DEBUG) Log.d("TAG" , "checked changed !! false" );
                    snackbar.show();
                }
            }
        });


        return homeView;
    }


    public void startService(){
        Intent i = new Intent(getActivity() , screenOff.class);
        getActivity().startService(i);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("START_CHECK" , true);
        editor.commit();
        if(DEBUG) Log.d("TAG" , "checked changed !! true" );
    }

    public void stopService(){
        Intent i = new Intent(getActivity() , screenOff.class );
        getActivity().stopService(i);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("START_CHECK" , false);
        editor.commit();
    }




}
