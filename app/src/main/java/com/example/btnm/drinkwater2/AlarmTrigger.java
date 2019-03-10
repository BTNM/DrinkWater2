package com.example.btnm.drinkwater2;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.widget.Toast;

// alarm receiver/trigger class that takes alarm and initiate them
public class AlarmTrigger extends BroadcastReceiver {

    // alarm/action that are triggered with the alarm manager intent
    @Override
    public void onReceive(Context context, Intent intent) {
//        System.out.println("testing alarmTrigger1");
//        Log.d("Alarm USer Notice", "TRIGGERED");

//        PowerManager.WakeLock wakeLock;
//        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
//        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"DrinkWater::MyWakelockTag" );
//        wakeLock.acquire();

        Toast.makeText(context,"alarm are running from trigger finally", Toast.LENGTH_LONG).show();

//        Intent startAutoSyncService = new Intent(context, AlarmTrigger.class);
//        context.startService(startAutoSyncService);
//
//        wakeLock.release();
    }


}
