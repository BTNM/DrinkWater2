package com.example.btnm.drinkwater2.tabFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.btnm.drinkwater2.AlarmDatabase;
import com.example.btnm.drinkwater2.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private static final String TAG = "Tab Home";

    private ArrayList<Integer> alarmRequestCodeList = new ArrayList<>();
    private AlarmDatabase alarmDatabase;

    private Switch toggle15m, toggle30m, toggle45m, toggle1h, toggle1_5h, toggle2h;

    private static final String FILE_NAME = "internalStorageTest.txt";
   Button testBtn1, testBtn2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_home,container, false);

        alarmDatabase = new AlarmDatabase(getContext(), alarmRequestCodeList);

        setupAlarmSwitches(view);


        return view;
    }
    public void writeFile () {
        String inputText = "testing write to file";
//        String fileName = "App test file.txt";
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = getContext().openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fileOutputStream.write(inputText.getBytes());
//            fileOutputStream.close();

            Toast.makeText(getContext(), "test text saved to " + getContext().getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void readFile () {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = getContext().openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(getContext().openFileInput(FILE_NAME)) );

            StringBuffer stringBuffer = new StringBuffer();

            String tempLines;
            while ((tempLines = bufferedReader.readLine()) != null ) {
                stringBuffer.append(tempLines + "\n");
            }

            Toast.makeText(getContext(), stringBuffer.toString(), Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * switches connected to the user interface
     * @param view
     */
    private void setupAlarmSwitches(View view) {
        testBtn1 = view.findViewById(R.id.btnTest);
        testBtn1.setOnClickListener( e -> {
            writeFile();
        });
        testBtn2 = view.findViewById(R.id.alarmTestBtn);
        testBtn2.setOnClickListener( e -> {
            readFile();
        });

        toggle15m = view.findViewById(R.id.switch15m);
        toggle15m.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // toogle enabled
            if (isChecked) {
                checkSwitches(toggle15m);
//                Toast.makeText(getActivity(), "Switch 10m On", Toast.LENGTH_SHORT).show();
//                startAlarm("Switch 10m On");

//                startAlarmWithRequestCode(alarmRequestCodeList.get(alarmRequestCodeList.indexOf(convertHourMinToRequestCode(0,15)) ) );
                alarmDatabase.startAlarmWithRequestCode(0,15);

            } else {
//                Toast.makeText(getActivity(), "Switch 10m Off", Toast.LENGTH_SHORT).show();
//                System.out.println(" check from alarmdatabase: "+ alarmRequestCodeList.indexOf(convertHourMinToRequestCode(0,15)));
                alarmDatabase.cancelAlarm(0, 15);
            }
        } );

        toggle30m = view.findViewById(R.id.switch30m);
        toggle30m.setOnCheckedChangeListener( ((buttonView, isChecked) -> {
            if (isChecked) {
                checkSwitches(toggle30m);
                alarmDatabase.startAlarmWithRequestCode(0,30);
            } else {
                alarmDatabase.cancelAlarm(0, 30);

            }
        }));

        toggle45m = view.findViewById(R.id.switch45m);
        toggle45m.setOnCheckedChangeListener( ((buttonView, isChecked) -> {
            if (isChecked) {
                checkSwitches(toggle45m);
                alarmDatabase.startAlarmWithRequestCode(0,45);
            } else {
//                cancelAlarm();
                alarmDatabase.cancelAlarm(0, 45);
            }
        }));

        toggle1h = view.findViewById(R.id.switch1h);
        toggle1h.setOnCheckedChangeListener( ((buttonView, isChecked) -> {
            if (isChecked) {
                checkSwitches(toggle1h);
                alarmDatabase.startAlarmWithRequestCode(1,0);
            } else {
                alarmDatabase.cancelAlarm(1, 0);
            }
        }));

        toggle1_5h = view.findViewById(R.id.switch1_5h);
        toggle1_5h.setOnCheckedChangeListener( ((buttonView, isChecked) -> {
            if (isChecked) {
                checkSwitches(toggle1_5h);
                alarmDatabase.startAlarmWithRequestCode(1,30);
            } else {
                alarmDatabase.cancelAlarm(1, 30);
            }
        }));

        toggle2h= view.findViewById(R.id.switch2h);
        toggle2h.setOnCheckedChangeListener( ((buttonView, isChecked) -> {
            if (isChecked) {
                checkSwitches(toggle2h);
                alarmDatabase.startAlarmWithRequestCode(2,0);
            } else {
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

