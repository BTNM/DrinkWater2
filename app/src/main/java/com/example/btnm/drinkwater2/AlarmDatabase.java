package com.example.btnm.drinkwater2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import java.util.ArrayList;

public class AlarmDatabase {

    private AlarmManager alarmManager;
    private ArrayList<Integer> alarmCodeList;
    private Context context;


    /**
     * Constructor of alarm database, with an arraylist with all keys for request code to identify the alarms in the alarm manager
     * @param context
     * @param alarmTimeDatabase
     */
    public AlarmDatabase(Context context, ArrayList<Integer> alarmTimeDatabase) {
        this.alarmCodeList = alarmTimeDatabase;
        this.context = context;
    }


    /**
     * cancel alarm in the alarm manager based on time key converted used in pending Intent
     * create a pending Intent with same makeup, to cancel alarm
     * @param hour
     * @param min
     */
    public void cancelAlarm(int hour, int min) {
        int requestCode = convertHourMinToRequestCode(hour, min);

        int index = alarmCodeList.indexOf(requestCode);
        int pickedAlarmTime = alarmCodeList.get(index );

        Intent intent = new Intent( context, NotificationReceiver.class);
        PendingIntent cancelPendingIntent = PendingIntent.getBroadcast(context, pickedAlarmTime, intent, 0);

        alarmManager.cancel(cancelPendingIntent);

        System.out.println("in database remove requestCode: " + pickedAlarmTime+ " index: "+ index);
        alarmCodeList.remove(index);
    }

//    public void startAlarm () {
//
//        int alarmNumber = 0;
//
//        // create a alarmmanager and a pendingIntent, while saving the pendingIntent
//        alarmManager = (AlarmManager) context.getSystemService( Context.ALARM_SERVICE);
//        Intent intent = new Intent(context , NotificationReceiver.class);
//
//        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, alarmNumber, intent, 0);
//
//
////        Millisec * Second * Minutes, setInexctRepeating minimum interval about 1 min
//        int minutes = 1;
//        int interval = 1000*60*minutes;
//
//        // set the alarm with time interval
//        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+ 1000*2, interval, alarmPendingIntent);
//
//    }


    /**
     * Start repeating alarm notification with alarm manager
     * @param hour how many hours in alarm
     * @param min how many minutes in alarm
     */
    public void startAlarmWithRequestCode(int hour, int min) {
        // create a alarmmanager and a pendingIntent to set repeating alarm

        int requestCode = convertHourMinToRequestCode(hour, min);

        alarmManager = (AlarmManager) context.getSystemService( Context.ALARM_SERVICE);
        Intent intent = new Intent(context , NotificationReceiver.class);
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0);

        alarmCodeList.add(requestCode);
//        System.out.println("in database add requestCode: " + requestCode);

//        Millisec * Second * Minutes, setInexctRepeating minimum interval about 1 min
        int interval = 1000*60*requestCode;

        // set the alarm with time interval
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+ 1000*2, interval, alarmPendingIntent);
    }


    /**
     * convert all hours and minutes into minutes to be used as alarm key
     * @param hour
     * @param minute
     * @return  return converted time into minutes, a key for the alarm
     */
    public int convertHourMinToRequestCode (int hour, int minute) {
        int temp = 0;
        if (hour == 0) {
            temp = minute;
        } else {
            temp = 60*hour + minute;
        }
        return temp;
    }

    public ArrayList<Integer> getAlarmCodeList() {
        return alarmCodeList;
    }

    public void setAlarmCodeList(ArrayList<Integer> alarmCodeList) {
        this.alarmCodeList = alarmCodeList;
    }


}
