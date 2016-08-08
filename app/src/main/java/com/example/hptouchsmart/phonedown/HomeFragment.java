package com.example.hptouchsmart.phonedown;


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
        boolean stateCheck = checkPrefrences.getBoolean("START_CHECK" , false);

        if(stateCheck){
            toggleButton.setChecked(true);
            Log.d("TAG" , "boolean true");
        }else{
            toggleButton.setChecked(false);
            Log.d("TAG" , "boolean false");

        }

        Log.d("TAG" , "Home Fragment Called !!");
        homeView.requestLayout();

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Intent i = new Intent(getActivity() , screenOff.class);
                    getActivity().startService(i);
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("START_CHECK" , true);
                    editor.commit();
                    Log.d("TAG" , "checked changed !! true" );
                    Snackbar snackbar = Snackbar.make(homeView , "Silent Flip ON" , Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }else{

                    Intent i = new Intent(getActivity() , screenOff.class );
                    getActivity().stopService(i);
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("START_CHECK" , false);
                    editor.commit();
                    Snackbar snackbar = Snackbar.make(homeView , "Silent Flip OFF" , Snackbar.LENGTH_SHORT);
                    Log.d("TAG" , "checked changed !! false" );
                    snackbar.show();
                }
            }
        });

        return homeView;
    }

}
