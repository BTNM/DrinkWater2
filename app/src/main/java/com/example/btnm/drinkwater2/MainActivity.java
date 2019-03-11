package com.example.btnm.drinkwater2;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.btnm.drinkwater2.tabFragments.AlarmsFragment;
import com.example.btnm.drinkwater2.tabFragments.HomeFragment;
import com.example.btnm.drinkwater2.tabFragments.SettingFragments;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setDisplayShowHomeEnabled(false);



        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());

        //ViewPagers animates screen slides automatically, then add the different tabs/fragment to the viewpager
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        //connect the vierpager to the tablelayout in the xml
        TabLayout tableLayout = (TabLayout) findViewById(R.id.allTabs);
        tableLayout.setupWithViewPager(mViewPager);

        System.out.println(" app context 1: "+ getApplicationContext() );
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)

    }

    private void setupViewPager(ViewPager viewPager) {
        // SectionPageAdapter extends FragmentPageAdapter to add all fragments into a fragment list
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(),"Home");
        adapter.addFragment(new AlarmsFragment(),"Alarm List");
        adapter.addFragment(new SettingFragments(),"Setting");

        viewPager.setAdapter(adapter);
    }

//    public void startAlarmFromMain() {
////        Toast.makeText(this,"alarm are running2", Toast.LENGTH_LONG).show();
//
//        AlarmManager alarmManager = (AlarmManager) getSystemService( Context.ALARM_SERVICE);
////        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(MainActivity.this, NotificationReceiver.class);
////        Intent intent = new Intent(getActivity(), NotificationReceiver.class);
//        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
//
////        Millisec * Second * Minutes
//        int interval = 1000*10;
//
//
//        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+ 1000*2, interval, alarmPendingIntent);
//    }

}
