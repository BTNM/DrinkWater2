package com.example.btnm.drinkwater2;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

// alarm receiver/trigger class that takes alarm and initiate them
public class AlarmTrigger extends BroadcastReceiver {

    // alarm/action that are triggered with the alarm manager intent
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("testing alarmTrigger1");
        Log.d("Alarm USer Notice", "TRIGGERED");
        Toast.makeText(context,"alarm are running", Toast.LENGTH_LONG).show();

//        Intent notificationIntent = new Intent(context, AlarmTrigger.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
//
//        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


    }


}
