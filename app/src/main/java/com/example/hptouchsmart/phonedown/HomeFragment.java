package com.example.hptouchsmart.phonedown;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
        View homeView = inflater.inflate(R.layout.fragment_home, container, false);

        ToggleButton toggleButton = (ToggleButton) homeView.findViewById(R.id.toggleStartService);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Intent i = new Intent(getActivity() , screenOff.class);
                    getActivity().startService(i);

                }else{

                    Intent i = new Intent(getActivity() , screenOff.class );
                    getActivity().stopService(i);

                }
            }
        });

        return homeView;
    }

}
