package com.example.btnm.drinkwater2.tabFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.example.btnm.drinkwater2.AlarmDatabase;
import com.example.btnm.drinkwater2.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private static final String TAG = "Tab Home";

//    private AlarmManager alarmManager;
    private ArrayList<Integer> alarmRequestCodeList = new ArrayList<>();
    private AlarmDatabase alarmDatabase;

    private Switch toggle15m, toggle30m, toggle45m, toggle1h, toggle1_5h, toggle2h;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_home,container, false);

        alarmDatabase = new AlarmDatabase(getContext(), alarmRequestCodeList);

//        alarmRequestCodeList.add(111);

        setupAlarmSwitches(view);



        return view;
    }

    private void setupAlarmSwitches(View view) {
        int requestCode = 0;

//        int test = alarmRequestCodeList.indexOf( convertHourMinToRequestCode(1,51) );
//        System.out.println("test convertHMRC" + test );

        // switch connected to user interface
        toggle15m = view.findViewById(R.id.switch15m);
        toggle15m.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // toogle enabled
            if (isChecked) {
                checkSwitches(toggle15m);
//                Toast.makeText(getActivity(), "Switch 10m On", Toast.LENGTH_SHORT).show();
//                startAlarm("Switch 10m On");

//                startAlarmWithRequestCode(alarmRequestCodeList.get(alarmRequestCodeList.indexOf(convertHourMinToRequestCode(0,15)) ) );
//                System.out.println("test convertHourMinRC: "+ convertHourMinToRequestCode(0,15)+ " check from alarmdatabase: "+ alarmRequestCodeList.indexOf(convertHourMinToRequestCode(0,15)) );

                alarmDatabase.startAlarmWithRequestCode(0,15);

//                alarmDatabase.getAlarmListDatabase().add(alarmDatabase.convertHourMinToRequestCode(0,15));
//                alarmDatabase.startAlarmWithRequestCode(alarmDatabase.getAlarmListDatabase().get(alarmDatabase.getAlarmListDatabase().indexOf(alarmDatabase.convertHourMinToRequestCode(0,15))) );


            } else {
//                Toast.makeText(getActivity(), "Switch 10m Off", Toast.LENGTH_SHORT).show();
//                System.out.println(" check from alarmdatabase: "+ alarmRequestCodeList.indexOf(convertHourMinToRequestCode(0,15)));

                alarmDatabase.cancelAlarm(0, 15);

//                cancelAlarm(alarmRequestCodeList.get(alarmRequestCodeList.indexOf( convertHourMinToRequestCode(0,15)  ) ));
//                alarmRequestCodeList.remove(alarmRequestCodeList.indexOf( convertHourMinToRequestCode(0,15)  ));


            }
        } );

        toggle30m = view.findViewById(R.id.switch30m);
        toggle30m.setOnCheckedChangeListener( ((buttonView, isChecked) -> {
//            requestCode = mainActivity.convertHourMinToRequestCode(0,30);

            if (isChecked) {
                checkSwitches(toggle30m);
//                mainActivity.startAlarmWithRequestCode(requestCode );
//                alarmRequestCodeList.add(mainActivity.convertHourMinToRequestCode(0,30) );
//                mainActivity.startAlarmWithRequestCode(alarmRequestCodeList.get(alarmRequestCodeList.indexOf( mainActivity.convertHourMinToRequestCode(0,30) ) ) );
                alarmDatabase.startAlarmWithRequestCode(0,30);
            } else {
                alarmDatabase.cancelAlarm(0, 30);
//                mainActivity.cancelAlarm(requestCode );
//                mainActivity.cancelAlarm(alarmRequestCodeList.get(alarmRequestCodeList.indexOf( mainActivity.convertHourMinToRequestCode(0,30) ) ) );
//                alarmRequestCodeList.remove(mainActivity.convertHourMinToRequestCode(0,30));
            }
        }));

        toggle45m = view.findViewById(R.id.switch45m);
        toggle45m.setOnCheckedChangeListener( ((buttonView, isChecked) -> {
            if (isChecked) {
//                checkSwitches(toggle45m);
//                startAlarmWithRequestCode(10);
                alarmDatabase.startAlarmWithRequestCode(0,45);
            } else {
//                cancelAlarm();
                alarmDatabase.cancelAlarm(0, 45);
            }
        }));

        toggle1h = view.findViewById(R.id.switch1h);
        toggle1h.setOnCheckedChangeListener( ((buttonView, isChecked) -> {
            if (isChecked) {
//                checkSwitches(toggle1h);
//                startAlarmWithRequestCode(20);
                alarmDatabase.startAlarmWithRequestCode(1,0);
            } else {
//                cancelAlarm();
                alarmDatabase.cancelAlarm(1, 0);
            }
        }));

        toggle1_5h = view.findViewById(R.id.switch1_5h);
        toggle1_5h.setOnCheckedChangeListener( ((buttonView, isChecked) -> {
            if (isChecked) {
                checkSwitches(toggle1_5h);
//                startAlarm();
                alarmDatabase.startAlarmWithRequestCode(1,30);
            } else {
//                cancelAlarm();
                alarmDatabase.cancelAlarm(1, 30);
            }
        }));

        toggle2h= view.findViewById(R.id.switch2h);
        toggle2h.setOnCheckedChangeListener( ((buttonView, isChecked) -> {
            if (isChecked) {
                checkSwitches(toggle2h);
//                startAlarm();
                alarmDatabase.startAlarmWithRequestCode(2,0);
            } else {
//                cancelAlarm();
                alarmDatabase.cancelAlarm(2, 0);
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
//        Intent intent = new Intent(getContext() , NotificationReceiver.class);
//        PendingIntent cancelPendingIntent = PendingIntent.getBroadcast(getContext(), requestCode, intent, 0);
//
//        alarmManager.cancel(cancelPendingIntent);
//    }
//
//
//    public void startAlarmWithRequestCode(int requestCode) {
////        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
////        Intent intent = new Intent(getActivity(), NotificationReceiver.class);
//
//        // create a alarmmanager and a pendingIntent, while saving the pendingIntent
//        alarmManager = (AlarmManager) getContext().getSystemService( Context.ALARM_SERVICE);
//        Intent intent = new Intent(getContext() , NotificationReceiver.class);
//
//        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(getContext(), requestCode, intent, 0);
//
////        alarmLinkedList.add(alarmPendingIntent);
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

