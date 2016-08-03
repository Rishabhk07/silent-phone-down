package com.example.hptouchsmart.phonedown;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
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

    //FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    SettingFragment settingFragment;
    HomeFragment homeFragment;
    AboutFragment aboutFragment;
    android.app.FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

                drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                actionBarDrawerToggle = new ActionBarDrawerToggle(this , drawerLayout  , toolbar , R.string.drawer_open , R.string.drawer_close);
                navigationView = (NavigationView) findViewById(R.id.navgation_view);



                drawerLayout.setDrawerListener(actionBarDrawerToggle);


                settingFragment = new SettingFragment();
                homeFragment = new HomeFragment();
                aboutFragment = new AboutFragment();

                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.FragementFrame , homeFragment , null);
                fragmentTransaction.commit();





            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {

                    switch (item.getItemId()){


                        case R.id.home:

                            fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.FragementFrame , homeFragment , null);
                            fragmentTransaction.commit();
                            Log.d("TAG"  , "home Called");
                            getSupportActionBar().setTitle("Silent Flip");
                            item.setChecked(true);
                            drawerLayout.closeDrawers();

                            break;

                        case R.id.setting:

                            fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.FragementFrame , settingFragment , null);
                            fragmentTransaction.commit();
                            getSupportActionBar().setTitle("Settings");
                            item.setChecked(true);
                            drawerLayout.closeDrawers();

                            break;

                        case R.id.about:

                            fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.FragementFrame , aboutFragment, null);
                            fragmentTransaction.commit();
                            getSupportActionBar().setTitle("About");
                            item.setChecked(true);
                            drawerLayout.closeDrawers();

                            break;


                    }

                    return false;
                }
            });





    }


    @Override
    protected void onResume() {
        super.onResume();
       Log.d("TAG" , "onResume called !! ");

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState();
    }
}
