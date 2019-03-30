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

import com.example.btnm.drinkwater2.MainActivity;
import com.example.btnm.drinkwater2.NotificationReceiver;
import com.example.btnm.drinkwater2.R;

import java.util.ArrayList;
import java.util.LinkedList;

public class HomeFragment extends Fragment {
    private static final String TAG = "Tab Home";

    private AlarmManager alarmManager;
    private LinkedList<PendingIntent> alarmLinkedList = new LinkedList<>();
//    private Map<Integer, PendingIntent> alarmMap;

    MainActivity mainActivity;

    public ArrayList<Integer> alarmTimeDatabase = new ArrayList<>();

    private int alarmNumber = 0;

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

        alarmTimeDatabase.add(111);

        setupAlarmSwitches(view);



        return view;
    }

    public int getValueAlarmDatabaseFromTimePeriod(int time) {
        int temp = alarmTimeDatabase.get(alarmTimeDatabase.indexOf(time ) );

        return temp;
    }

    private void setupAlarmSwitches(View view) {
        int requestCode = 0;

//        int test = alarmTimeDatabase.indexOf( convertHourMinToRequestCode(1,51) );
//        System.out.println("test convertHMRC" + test );


        // switch connected to user interface
        toggle15m = view.findViewById(R.id.switch15m);
        toggle15m.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // toogle enabled
            if (isChecked) {
                checkSwitches(toggle15m);
//                Toast.makeText(getActivity(), "Switch 10m On", Toast.LENGTH_SHORT).show();
//                startAlarm("Switch 10m On");

//                alarmTimeDatabase.add(convertHourMinToRequestCode(0,15) );
//                startAlarmWithRequestCode(alarmTimeDatabase.get(alarmTimeDatabase.indexOf(convertHourMinToRequestCode(0,15)) ) );
//                System.out.println("test convertHourMinRC: "+ convertHourMinToRequestCode(0,15)+ " check from alarmdatabase: "+ alarmTimeDatabase.indexOf(convertHourMinToRequestCode(0,15)) );

                alarmTimeDatabase.add(convertHourMinToRequestCode(0,15));
                startAlarmWithRequestCode(getValueAlarmDatabaseFromTimePeriod(convertHourMinToRequestCode(0,15) ));

            } else {
//                Toast.makeText(getActivity(), "Switch 10m Off", Toast.LENGTH_SHORT).show();
//                System.out.println(" check from alarmdatabase: "+ alarmTimeDatabase.indexOf(convertHourMinToRequestCode(0,15)));
                cancelAlarm(alarmTimeDatabase.get(alarmTimeDatabase.indexOf( convertHourMinToRequestCode(0,15)  ) ));
                alarmTimeDatabase.remove(alarmTimeDatabase.indexOf( convertHourMinToRequestCode(0,15)  ));

            }
        } );

        toggle30m = view.findViewById(R.id.switch30m);
        toggle30m.setOnCheckedChangeListener( ((buttonView, isChecked) -> {
//            requestCode = mainActivity.convertHourMinToRequestCode(0,30);

            if (isChecked) {
                checkSwitches(toggle30m);
//                mainActivity.startAlarmWithRequestCode(requestCode );
//                alarmTimeDatabase.add(mainActivity.convertHourMinToRequestCode(0,30) );
//                mainActivity.startAlarmWithRequestCode(alarmTimeDatabase.get(alarmTimeDatabase.indexOf( mainActivity.convertHourMinToRequestCode(0,30) ) ) );
            } else {
//                mainActivity.cancelAlarm(requestCode );
//                mainActivity.cancelAlarm(alarmTimeDatabase.get(alarmTimeDatabase.indexOf( mainActivity.convertHourMinToRequestCode(0,30) ) ) );
//                alarmTimeDatabase.remove(mainActivity.convertHourMinToRequestCode(0,30));
            }
        }));

        toggle45m = view.findViewById(R.id.switch45m);
        toggle45m.setOnCheckedChangeListener( ((buttonView, isChecked) -> {
            if (isChecked) {
//                checkSwitches(toggle45m);
//                startAlarmWithRequestCode(10);
            } else {
//                cancelAlarm();
            }
        }));

        toggle1h = view.findViewById(R.id.switch1h);
        toggle1h.setOnCheckedChangeListener( ((buttonView, isChecked) -> {
            if (isChecked) {
//                checkSwitches(toggle1h);
//                startAlarmWithRequestCode(20);
            } else {
//                cancelAlarm();
            }
        }));

        toggle1_5h = view.findViewById(R.id.switch1_5h);
        toggle1_5h.setOnCheckedChangeListener( ((buttonView, isChecked) -> {
            if (isChecked) {
                checkSwitches(toggle1_5h);
//                startAlarm();
            } else {
//                cancelAlarm();
            }
        }));

        toggle2h= view.findViewById(R.id.switch2h);
        toggle2h.setOnCheckedChangeListener( ((buttonView, isChecked) -> {
            if (isChecked) {
                checkSwitches(toggle2h);
//                startAlarm();
            } else {
//                cancelAlarm();
            }
        }));


    }

    public void checkSwitches (Switch toggleSwitch) {
//        boolean toggle = toggleSwitch.isChecked();

        if (toggle15m == toggleSwitch) {
            toggle30m.setChecked(false);
            toggle45m.setChecked(false);
            toggle1h.setChecked(false);
            toggle1_5h.setChecked(false);
            toggle2h.setChecked(false);
        }
        if (toggle30m == toggleSwitch) {
            toggle15m.setChecked(false);
            toggle45m.setChecked(false);
            toggle1h.setChecked(false);
            toggle1_5h.setChecked(false);
            toggle2h.setChecked(false);
        }
        if (toggle45m == toggleSwitch) {
            toggle15m.setChecked(false);
            toggle30m.setChecked(false);
            toggle1h.setChecked(false);
            toggle1_5h.setChecked(false);
            toggle2h.setChecked(false);
        }
        if (toggle1h == toggleSwitch) {
            toggle15m.setChecked(false);
            toggle30m.setChecked(false);
            toggle45m.setChecked(false);
            toggle1_5h.setChecked(false);
            toggle2h.setChecked(false);
        }
        if (toggle1_5h == toggleSwitch) {
            toggle15m.setChecked(false);
            toggle30m.setChecked(false);
            toggle45m.setChecked(false);
            toggle1h.setChecked(false);
            toggle2h.setChecked(false);
        }
        if (toggle2h == toggleSwitch) {
            toggle15m.setChecked(false);
            toggle30m.setChecked(false);
            toggle45m.setChecked(false);
            toggle1h.setChecked(false);
            toggle1_5h.setChecked(false);
        }

    }

    public int convertHourMinToRequestCode (int hour, int minute) {
        int temp = 0;
        if (hour == 0) {
            temp = minute;
        } else {
            temp = 60*hour + minute;
        }
        return temp;
    }

        public void cancelAlarm(int requestCode) {
        Intent intent = new Intent(getContext() , NotificationReceiver.class);
        PendingIntent cancelPendingIntent = PendingIntent.getBroadcast(getContext(), requestCode, intent, 0);

        alarmManager.cancel(cancelPendingIntent);
    }

    public void startAlarm () {
//        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(getActivity(), NotificationReceiver.class);

        // create a alarmmanager and a pendingIntent, while saving the pendingIntent
        alarmManager = (AlarmManager) getContext().getSystemService( Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext() , NotificationReceiver.class);

        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(getContext(), alarmNumber, intent, 0);

        alarmLinkedList.add(alarmPendingIntent);

//        Millisec * Second * Minutes, setInexctRepeating minimum interval about 1 min
        int minutes = 1;
        int interval = 1000*60*minutes;

        // set the alarm with time interval
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+ 1000*2, interval, alarmPendingIntent);

//        alarmLinkedList.indexOf(alarmPendingIntent);


//        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_FIFTEEN_MINUTES, AlarmManager.INTERVAL_HALF_HOUR, alarmPendingIntent);
//        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, System.currentTimeMillis(), 1000*10, alarmPendingIntent);

    }

    public void startAlarmWithRequestCode(int requestCode) {
//        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(getActivity(), NotificationReceiver.class);

        // create a alarmmanager and a pendingIntent, while saving the pendingIntent
        alarmManager = (AlarmManager) getContext().getSystemService( Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext() , NotificationReceiver.class);

        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(getContext(), requestCode, intent, 0);

        alarmLinkedList.add(alarmPendingIntent);

//        Millisec * Second * Minutes, setInexctRepeating minimum interval about 1 min
        int minutes = 1;
        int interval = 1000*60*minutes;

        // set the alarm with time interval
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+ 1000*2, interval, alarmPendingIntent);

//        alarmLinkedList.indexOf(alarmPendingIntent);


//        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_FIFTEEN_MINUTES, AlarmManager.INTERVAL_HALF_HOUR, alarmPendingIntent);
//        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, System.currentTimeMillis(), 1000*10, alarmPendingIntent);

    }


}

