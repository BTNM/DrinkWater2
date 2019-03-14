package com.example.btnm.drinkwater2.tabFragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
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
import android.widget.Switch;
import android.widget.Toast;

import com.example.btnm.drinkwater2.NotificationReceiver;
import com.example.btnm.drinkwater2.R;

import java.util.LinkedHashMap;
import java.util.Map;

public class HomeFragment extends Fragment {
    private static final String TAG = "Tab Home";

    private AlarmManager alarmManager;
    private PendingIntent tempAlarmPendingIntent;
    private Map<String, PendingIntent> alarmMap;

    private Button alarmTestBtn;
    private Switch toggle15m;
    private Switch toggle30m;
    private Switch toggle45m;
    private Switch toggle1h;
    private Switch toggle1_5h;
    private Switch toggle2h;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_home,container, false);



        setupAlarmSwitches(view);


        return view;
    }

    public void setupAlarmList() {
        alarmMap = new LinkedHashMap();



    }

    private void setupAlarmSwitches(View view) {

        alarmTestBtn = (Button) view.findViewById(R.id.alarmTestBtn);
        alarmTestBtn.setOnClickListener((v) -> {
//            Toast.makeText(getContext(), "alarm btn test", Toast.LENGTH_SHORT).show();
            startAlarm();

        });

        // switch connected to user interface
        toggle15m = view.findViewById(R.id.switch15m);
        toggle15m.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // toogle enabled
            if (isChecked) {
//                    Toast.makeText(getActivity(), "Switch 10m On", Toast.LENGTH_SHORT).show();
                tempAlarmPendingIntent = startAlarm();
                System.out.println("alarm pendingintent: "+tempAlarmPendingIntent.toString());
//                alarmMap.put("",tempAlarmPendingIntent);
            } else {
                Toast.makeText(getActivity(), "Switch 10m Off", Toast.LENGTH_SHORT).show();
//                alarmManager.cancel(alarmPendingIntent);
            }
        } );
        toggle30m = view.findViewById(R.id.switch30m);
        toggle30m.setOnCheckedChangeListener( ((buttonView, isChecked) -> {
            if (isChecked) {

            } else {

            }
        }));

        toggle45m = view.findViewById(R.id.switch45m);
        toggle45m.setOnCheckedChangeListener( ((buttonView, isChecked) -> {
            if (isChecked) {

            } else {

            }
        }));

        toggle1h = view.findViewById(R.id.switch1h);
        toggle1h.setOnCheckedChangeListener( ((buttonView, isChecked) -> {
            if (isChecked) {

            } else {

            }
        }));

        toggle1_5h = view.findViewById(R.id.switch1_5h);
        toggle1_5h.setOnCheckedChangeListener( ((buttonView, isChecked) -> {
            if (isChecked) {

            } else {

            }
        }));
        
        toggle2h= view.findViewById(R.id.switch2h);
        toggle2h.setOnCheckedChangeListener( ((buttonView, isChecked) -> {
            if (isChecked) {

            } else {

            }
        }));


    }


    public PendingIntent startAlarm () {
        alarmManager = (AlarmManager) getContext().getSystemService( Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext() , NotificationReceiver.class);
//        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(getActivity(), NotificationReceiver.class);
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, 0);

        int minutes = 1;
//        Millisec * Second * Minutes, setInexctRepeating minimum interval about 1 min
        int interval = 1000*60*minutes;

        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+ 1000*2, interval, alarmPendingIntent);

        return alarmPendingIntent;
//        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_FIFTEEN_MINUTES, AlarmManager.INTERVAL_HALF_HOUR, alarmPendingIntent);
//        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, System.currentTimeMillis(), 1000*10, alarmPendingIntent);

    }


}

