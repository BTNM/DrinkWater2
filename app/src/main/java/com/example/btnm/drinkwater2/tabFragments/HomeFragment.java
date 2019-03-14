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
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.btnm.drinkwater2.NotificationReceiver;
import com.example.btnm.drinkwater2.R;

import java.util.LinkedHashMap;
import java.util.Map;

public class HomeFragment extends Fragment {
    private static final String TAG = "Tab Home";

    private Button btnTest;
    Button alarmTestBtn;
    Switch toggle10m;

    private AlarmManager alarmManager;
    private PendingIntent tempAlarmPendingIntent;
    private Map<String, PendingIntent> alarmMap;


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

        btnTest = (Button) view.findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Testing home button click 1", Toast.LENGTH_SHORT).show();
            }
        });

        alarmTestBtn = (Button) view.findViewById(R.id.alarmTestBtn);
        alarmTestBtn.setOnClickListener((v) -> {
//            Toast.makeText(getContext(), "alarm btn test", Toast.LENGTH_SHORT).show();
            startAlarm();

        });

        // switch connected to user interface
        toggle10m = view.findViewById(R.id.switch15m);
        toggle10m.setOnCheckedChangeListener((buttonView, isChecked) -> {
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

//        toggle10m = view.findViewById(R.id.switch10m);
//        toggle10m.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    // toogle enabled
////                    Toast.makeText(getActivity(), "Switch 10m On", Toast.LENGTH_SHORT).show();
////                    ((MainActivity) getActivity()).startAlarmFromMain();
//                    startAlarm();
//
//                } else {
//                    Toast.makeText(getActivity(), "Switch 10m Off", Toast.LENGTH_SHORT).show();
//                    alarmManager.cancel(alarmPendingIntent);
//                }
//            }
//        });




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

