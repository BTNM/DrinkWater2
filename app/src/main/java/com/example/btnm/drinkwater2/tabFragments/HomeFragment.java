package com.example.btnm.drinkwater2.tabFragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.btnm.drinkwater2.AlarmTrigger;
import com.example.btnm.drinkwater2.MainActivity;
import com.example.btnm.drinkwater2.R;

public class HomeFragment extends Fragment {
    private static final String TAG = "Tab Home";
    private Button btnTest;
    Switch toggle10m;

    View fragmentView;
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;
    BroadcastReceiver broadcastReceiver = null;

    Button alarmTestBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.tab_home,container, false);


//        Intent Intent = new Intent(getContext() , AlarmTrigger.class);
//        alarmIntent = PendingIntent.getBroadcast(getContext(), 0, Intent, 0);

//        alarmIntent = PendingIntent.getService(getContext(),0,alarmIntentTest,0);

//        alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
//        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_FIFTEEN_MINUTES,AlarmManager.INTERVAL_HALF_HOUR, alarmIntent);


//        btnTest = (Button) fragmentView.findViewById(R.id.btnTest);
//        btnTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(), "Testing home button click 1", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        alarmTestBtn = (Button) fragmentView.findViewById(R.id.alarmTestBtn);
//        alarmTestBtn.setOnClickListener((v) -> {
////            Toast.makeText(getContext(), "alarm btn test", Toast.LENGTH_SHORT).show();
//            startAlarm();
//        });
//
//        // switch connected to user interface
//        toggle10m = fragmentView.findViewById(R.id.switch10m);
//        toggle10m.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    // toogle enabled
////                    Toast.makeText(getActivity(), "Switch 10m On", Toast.LENGTH_SHORT).show();
//                    startAlarm();
//
//                } else {
//                    Toast.makeText(getActivity(), "Switch 10m Off", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        setupAlarms(fragmentView);

        return fragmentView;
    }

    private void setupAlarms(View fragmentView) {
        Intent intent = new Intent( getActivity().getApplicationContext(), AlarmTrigger.class);
        alarmIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);

        alarmTestBtn = (Button) fragmentView.findViewById(R.id.alarmTestBtn);
        alarmTestBtn.setOnClickListener((v) -> {
//            Toast.makeText(getContext(), "alarm btn test", Toast.LENGTH_SHORT).show();
            setAlarms();
        });

        btnTest = (Button) fragmentView.findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "Testing home button click 1", Toast.LENGTH_SHORT).show();
                stopAlarms();
            }
        });





    }

    private void stopAlarms() {
    }

    private void setAlarms() {
        startAlarm();
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//
//        broadcastReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                startAlarm();
//            }
//        };
//
//
//    }



    public void startAlarm () {


        alarmManager = (AlarmManager) (getActivity().getSystemService(Context.ALARM_SERVICE) );
////                getActivity().getApplicationContext().getSystemService(Context.ALARM_SERVICE);
//
//        Intent intent = new Intent(getActivity(), AlarmTrigger.class);
//        alarmIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);

        // Millisec * Second * Minutes
        int interval = 1000*3;

        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, System.currentTimeMillis(), interval, alarmIntent);
//        alarmManager.set(AlarmManager.ELAPSED_REALTIME, System.currentTimeMillis()+interval, alarmIntent);


        System.out.println("in startalarm context: "+getContext() + " getActivity: " + getActivity().getBaseContext() + " one more: " + getActivity().getApplication() );

//        alarmManager = (AlarmManager) getActivity().getApplicationContext().getSystemService(Context.ALARM_SERVICE);
//
//        Intent Intent = new Intent(getContext().getApplicationContext(), AlarmTrigger.class);
//        alarmIntent = PendingIntent.getBroadcast(getContext().getApplicationContext(), 0, Intent, 0);
//        int interval = 1000*2;
//        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, System.currentTimeMillis(), interval, alarmIntent);

//
//        alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
//
//        Intent Intent = new Intent(getContext() , AlarmTrigger.class);
//        alarmIntent = PendingIntent.getBroadcast(getContext(), 0, Intent, 0);
//        int interval = 1000*2;
//
//        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, System.currentTimeMillis(), interval, alarmIntent);
//        alarmManager.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime()+100, alarmIntent);


//        System.out.println("testing alarmTrigger2");
//        Toast.makeText(getActivity(), "test", Toast.LENGTH_SHORT).show();
    }


}

