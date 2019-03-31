package com.example.btnm.drinkwater2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.example.btnm.drinkwater2.tabFragments.AlarmsFragment;
import com.example.btnm.drinkwater2.tabFragments.HomeFragment;
import com.example.btnm.drinkwater2.tabFragments.SettingFragments;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;

//    private AlarmManager alarmManager;
//    //    private Map<Integer, PendingIntent> alarmMap;
//    private LinkedList<PendingIntent> alarmLinkedList = new LinkedList<>();
//    public static ArrayList<Integer> alarmListDatabase = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());

        //ViewPagers animates screen slides automatically, then add the different tabs/fragment to the viewpager
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        //connect the vierpager to the tablelayout in the xml
        TabLayout tableLayout = (TabLayout) findViewById(R.id.allTabs);
        tableLayout.setupWithViewPager(mViewPager);

        System.out.println(" app context 1: "+ getApplicationContext() );



    }

    private void setupViewPager(ViewPager viewPager) {
        // SectionPageAdapter extends FragmentPageAdapter to add all fragments into a fragment list
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(),"Home");
        adapter.addFragment(new AlarmsFragment(),"Alarm List");
        adapter.addFragment(new SettingFragments(),"Setting");

        viewPager.setAdapter(adapter);
    }

//    public int convertHourMinToRequestCode (int hour, int minute) {
//        int temp = 0;
//        if (hour == 0) {
//            temp = minute;
//        } else {
//            temp = 60*hour + minute;
//        }
//        return temp;
//    }
//
//    public void cancelAlarm(int requestCode) {
//        Intent intent = new Intent(getBaseContext() , NotificationReceiver.class);
//        PendingIntent cancelPendingIntent = PendingIntent.getBroadcast(getBaseContext(), requestCode, intent, 0);
//
//        alarmManager.cancel(cancelPendingIntent);
//    }
//
//    public void startAlarm () {
////        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
////        Intent intent = new Intent(getActivity(), NotificationReceiver.class);
//
//        // create a alarmmanager and a pendingIntent, while saving the pendingIntent
//        alarmManager = (AlarmManager) getBaseContext().getSystemService( Context.ALARM_SERVICE);
//        Intent intent = new Intent(getBaseContext() , NotificationReceiver.class);
//
//        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(getBaseContext(), 0, intent, 0);
//
//        alarmLinkedList.add(alarmPendingIntent);
//
////        Millisec * Second * Minutes, setInexctRepeating minimum interval about 1 min
//        int minutes = 1;
//        int interval = 1000*60*minutes;
//
//        // set the alarm with time interval
//        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+ 1000*2, interval, alarmPendingIntent);
//
////        alarmLinkedList.indexOf(alarmPendingIntent);
//
//
////        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_FIFTEEN_MINUTES, AlarmManager.INTERVAL_HALF_HOUR, alarmPendingIntent);
////        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, System.currentTimeMillis(), 1000*10, alarmPendingIntent);
//
//    }
//
//    public void startAlarmWithRequestCode(int requestCode) {
////        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
////        Intent intent = new Intent(getActivity(), NotificationReceiver.class);
//
//        // create a alarmmanager and a pendingIntent, while saving the pendingIntent
//        alarmManager = (AlarmManager) getBaseContext().getSystemService( Context.ALARM_SERVICE);
//        Intent intent = new Intent(getBaseContext() , NotificationReceiver.class);
//
//        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(getBaseContext(), requestCode, intent, 0);
//
//        alarmLinkedList.add(alarmPendingIntent);
//
////        Millisec * Second * Minutes, setInexctRepeating minimum interval about 1 min
//        int minutes = 1;
//        int interval = 1000*60*minutes;
//
//        // set the alarm with time interval
//        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+ 1000*2, interval, alarmPendingIntent);
//
////        alarmLinkedList.indexOf(alarmPendingIntent);
//
//
////        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_FIFTEEN_MINUTES, AlarmManager.INTERVAL_HALF_HOUR, alarmPendingIntent);
////        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, System.currentTimeMillis(), 1000*10, alarmPendingIntent);
//
//    }


}
