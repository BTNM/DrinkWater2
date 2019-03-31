package com.example.btnm.drinkwater2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class AlarmDatabase {

    private AlarmManager alarmManager;
    private ArrayList<Integer> alarmListDatabase;
    private Context context;

    public AlarmDatabase(Context context, ArrayList<Integer> alarmTimeDatabase) {
        this.alarmListDatabase = alarmTimeDatabase;
        this.context = context;
    }
    

    public void cancelAlarm(int hour, int min) {
        int requestCode = convertHourMinToRequestCode(hour, min);

        int index = alarmListDatabase.indexOf(requestCode);
        int pickedAlarmTime = alarmListDatabase.get(index );


        Intent intent = new Intent( context, NotificationReceiver.class);
        PendingIntent cancelPendingIntent = PendingIntent.getBroadcast(context, pickedAlarmTime, intent, 0);

        alarmManager.cancel(cancelPendingIntent);

        System.out.println("in database remove requestCode: " + pickedAlarmTime+ " index: "+ index);
        alarmListDatabase.remove(index);
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

    public void startAlarmWithRequestCode(int hour, int min) {
        // create a alarmmanager and a pendingIntent to set repeating alarm

        int requestCode = convertHourMinToRequestCode(hour, min);

        alarmManager = (AlarmManager) context.getSystemService( Context.ALARM_SERVICE);
        Intent intent = new Intent(context , NotificationReceiver.class);
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0);

        alarmListDatabase.add(requestCode);
        System.out.println("in database add requestCode: " + requestCode);

//        Millisec * Second * Minutes, setInexctRepeating minimum interval about 1 min
        int minutes = 1;
        int interval = 1000*60*minutes;

        // set the alarm with time interval
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+ 1000*2, interval, alarmPendingIntent);
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

    public ArrayList<Integer> getAlarmListDatabase() {
        return alarmListDatabase;
    }

    public void setAlarmListDatabase(ArrayList<Integer> alarmListDatabase) {
        this.alarmListDatabase = alarmListDatabase;
    }


}
